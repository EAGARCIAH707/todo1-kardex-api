package com.todo1.kardex.repository.kardex.impl;

import com.todo1.kardex.model.entities.KardexEntity;
import com.todo1.kardex.repository.kardex.IKardexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class KardexRepositoryImpl implements KardexRepositoryFacade {
  private final IKardexRepository kardexRepository;

  @Autowired
  public KardexRepositoryImpl(IKardexRepository kardexRepository) {
    this.kardexRepository = kardexRepository;
  }

  @Override
  public Optional<KardexEntity> save(KardexEntity kardexEntity) {
    return Optional.of(kardexRepository.save(kardexEntity));
  }

  @Override
  public Optional<KardexEntity> findById(Integer kardexId) {
    return kardexRepository.findById(kardexId);
  }

  @Override
  public List<KardexEntity> findAll() {
    return kardexRepository.findAll();
  }
}
