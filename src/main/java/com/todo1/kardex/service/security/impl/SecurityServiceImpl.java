package com.todo1.kardex.service.security.impl;

import com.todo1.kardex.configuration.JwtTokenUtil;
import com.todo1.kardex.service.security.ISecurityService;
import com.todo1.kardex.service.userdetail.UserDetailsServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SecurityServiceImpl implements ISecurityService {
  private final AuthenticationManager authenticationManager;
  private final UserDetailsServiceImpl userDetailsService;
  private final JwtTokenUtil jwtTokenUtil;

  @Autowired
  public SecurityServiceImpl(
      AuthenticationManager authenticationManager,
      UserDetailsServiceImpl userDetailsService,
      JwtTokenUtil jwtTokenUtil) {
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @Override
  public String authenticate(String email, String password) {
    log.trace("Authenticate,{},{}", email, password);
    var userDetails = userDetailsService.loadUserByUsername(email);
    var usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(email, password);
    authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    return jwtTokenUtil.generateToken(userDetails);
  }
}
