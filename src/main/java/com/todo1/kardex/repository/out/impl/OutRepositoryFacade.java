package com.todo1.kardex.repository.out.impl;

import com.todo1.kardex.model.entities.OutEntity;

import java.util.Optional;

public interface OutRepositoryFacade {
  Optional<OutEntity> save(OutEntity outEntity);
}
