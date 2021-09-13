package com.todo1.kardex.repository.out.impl;

import com.todo1.kardex.model.entities.OutEntity;
import com.todo1.kardex.repository.out.IOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OutRepositoryImpl implements OutRepositoryFacade {
  private final IOutRepository outRepository;

  @Autowired
  public OutRepositoryImpl(IOutRepository outRepository) {
    this.outRepository = outRepository;
  }

  @Override
  public Optional<OutEntity> save(OutEntity outEntity) {
    return Optional.of(outRepository.save(outEntity));
  }
}
