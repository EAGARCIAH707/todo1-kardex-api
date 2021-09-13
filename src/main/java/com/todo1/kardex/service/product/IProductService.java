package com.todo1.kardex.service.product;

import com.todo1.kardex.commons.exceptions.SystemException;
import com.todo1.kardex.model.dto.ProductDTO;
import com.todo1.kardex.model.entities.ProductEntity;

import java.util.List;

public interface IProductService {
  ProductDTO createProduct(ProductDTO productDTO) throws SystemException;

  List<ProductEntity> getAllProducts();
}
