package com.todo1.kardex.repository.user.impl;

import com.todo1.kardex.model.entities.UserEntity;

import java.util.Optional;

public interface UserRepositoryFacade {
  Optional<UserEntity> findUserByEmail(String email);

  UserEntity create(UserEntity user);
}
