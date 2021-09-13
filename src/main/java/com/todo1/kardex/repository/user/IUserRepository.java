package com.todo1.kardex.repository.user;

import com.todo1.kardex.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Integer> {
  Optional<UserEntity> findUserByEmail(String email);
}
