package com.todo1.kardex.service.user.impl;

import com.todo1.kardex.model.dto.UserDto;
import com.todo1.kardex.model.entities.UserEntity;
import com.todo1.kardex.repository.user.impl.UserRepositoryFacade;
import com.todo1.kardex.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.todo1.kardex.commons.util.Converter.converterObject;

@Service
public class UserServiceImpl implements IUserService {

  private final UserRepositoryFacade userRepository;

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserRepositoryFacade userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDto signUp(UserDto userDto) {
    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    var user = converterObject(userDto, UserEntity.class);
    user = userRepository.create(user);
    return converterObject(user, UserDto.class);
  }
}
