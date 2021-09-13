package com.todo1.kardex.repository.in.impl;

import com.todo1.kardex.model.entities.InEntity;

import java.util.Optional;

public interface InRepositoryFacade {
  Optional<InEntity> save(InEntity inEntity);
}
