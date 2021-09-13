package com.todo1.kardex.service.userdetail;

import com.todo1.kardex.repository.user.impl.UserRepositoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepositoryFacade userRepository;

  @Autowired
  public UserDetailsServiceImpl(UserRepositoryFacade userRespository) {
    this.userRepository = userRespository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) {
    var optUser = userRepository.findUserByEmail(email);
    if (optUser.isEmpty()) throw new UsernameNotFoundException(email);
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    grantedAuthorities.add(new SimpleGrantedAuthority(optUser.get().getRoleId().getRole()));
    return new org.springframework.security.core.userdetails.User(
        optUser.get().getEmail(), optUser.get().getPassword(), grantedAuthorities);
  }
}
