package com.todo1.kardex.repository.in.impl;

import com.todo1.kardex.model.entities.InEntity;
import com.todo1.kardex.repository.in.IInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InRepositoryImpl implements InRepositoryFacade {
  private final IInRepository inRepository;

  @Autowired
  public InRepositoryImpl(IInRepository inRepository) {
    this.inRepository = inRepository;
  }

  @Override
  public Optional<InEntity> save(InEntity inEntity) {
    return Optional.of(inRepository.save(inEntity));
  }
}
