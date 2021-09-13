package com.todo1.kardex.controller.user;

import com.todo1.kardex.model.dto.UserDto;
import com.todo1.kardex.model.dto.UserLoginDTO;
import com.todo1.kardex.service.security.ISecurityService;
import com.todo1.kardex.service.user.IUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.todo1.kardex.commons.constants.api.user.EndpointUser.LOGIN;
import static com.todo1.kardex.commons.constants.api.user.EndpointUser.SIGN_UP;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
@Log4j2
public class UserApi {

  private final ISecurityService securityService;

  private final IUserService userService;

  @Autowired
  public UserApi(ISecurityService securityService, IUserService userService) {
    this.securityService = securityService;
    this.userService = userService;
  }

  @PostMapping(LOGIN)
  public ResponseEntity<Void> login(@RequestBody UserLoginDTO userLoginDTO) {
    log.info("User Login  {}", userLoginDTO);
    String token =
        securityService.authenticate(userLoginDTO.getEmail(), userLoginDTO.getPassword());
    var headers = new HttpHeaders();
    headers.set("authorization", token);

    return ResponseEntity.noContent().headers(headers).build();
  }

  @PostMapping(SIGN_UP)
  public ResponseEntity<UserDto> signUp(@RequestBody UserDto userDto) {
    log.info("User signUp  {}", userDto);
    var response = userService.signUp(userDto);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
