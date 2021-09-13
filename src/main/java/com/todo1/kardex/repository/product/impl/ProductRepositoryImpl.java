package com.todo1.kardex.repository.product.impl;

import com.todo1.kardex.commons.exceptions.SystemException;
import com.todo1.kardex.model.entities.ProductEntity;
import com.todo1.kardex.repository.product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductRepositoryImpl implements ProductRepositoryFacade {
  private final IProductRepository productRepository;

  @Autowired
  public ProductRepositoryImpl(IProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Optional<ProductEntity> save(ProductEntity productEntity) throws SystemException {
    return Optional.of(productRepository.save(productEntity));
  }

  @Override
  public List<ProductEntity> findAll() {
    return productRepository.findAll();
  }
}
