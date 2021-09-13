package com.todo1.kardex.repository.kardex.impl;

import com.todo1.kardex.model.entities.KardexEntity;

import java.util.List;
import java.util.Optional;

public interface KardexRepositoryFacade {
  Optional<KardexEntity> save(KardexEntity kardexEntity);

  Optional<KardexEntity> findById(Integer kardexId);

  List<KardexEntity> findAll();
}
