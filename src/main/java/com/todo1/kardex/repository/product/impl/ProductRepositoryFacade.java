package com.todo1.kardex.repository.product.impl;

import com.todo1.kardex.commons.exceptions.SystemException;
import com.todo1.kardex.model.entities.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryFacade {
  Optional<ProductEntity> save(ProductEntity productEntity) throws SystemException;

  List<ProductEntity> findAll();
}
