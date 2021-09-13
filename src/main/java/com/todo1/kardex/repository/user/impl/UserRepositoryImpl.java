package com.todo1.kardex.repository.user.impl;

import com.todo1.kardex.model.entities.UserEntity;
import com.todo1.kardex.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepositoryFacade {
  private final IUserRepository userRepository;

  @Autowired
  public UserRepositoryImpl(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Optional<UserEntity> findUserByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }

  @Override
  public UserEntity create(UserEntity user) {
    return userRepository.save(user);
  }
}
