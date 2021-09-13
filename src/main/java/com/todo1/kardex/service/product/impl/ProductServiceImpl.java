package com.todo1.kardex.service.product.impl;

import com.todo1.kardex.commons.exceptions.SystemException;
import com.todo1.kardex.model.dto.ProductDTO;
import com.todo1.kardex.model.entities.ProductEntity;
import com.todo1.kardex.repository.product.impl.ProductRepositoryFacade;
import com.todo1.kardex.service.in.IInService;
import com.todo1.kardex.service.kardex.impl.KardexServiceImpl;
import com.todo1.kardex.service.product.IProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.todo1.kardex.commons.util.Converter.converterObject;

@Service
@Log4j2
public class ProductServiceImpl implements IProductService {

  private final ProductRepositoryFacade productRepository;

  private final KardexServiceImpl kardexService;

  private final IInService inService;

  @Autowired
  public ProductServiceImpl(
      ProductRepositoryFacade productRepository,
      KardexServiceImpl kardexService,
      IInService inService) {
    this.productRepository = productRepository;
    this.kardexService = kardexService;
    this.inService = inService;
  }

  @Override
  @Transactional
  public ProductDTO createProduct(ProductDTO productDTO) throws SystemException {
    log.info("Create product,{}", productDTO);
    if (productDTO.getQuantity() < 0) {
      throw new SystemException("It is not possible to create a product with a negative amount");
    }
    var product = productRepository.save(converterObject(productDTO, ProductEntity.class));
    if (product.isPresent()) {
      var kardex = kardexService.createKardexFromProduct(product.get());
      if (kardex.isPresent()) {
        inService.createInFromKardex(kardex.get());
        return converterObject(product.get(), ProductDTO.class);
      } else {
        log.error("Not possible create kardex,{}", kardex);
        throw new SystemException("Not possible create kardex for product");
      }
    } else {
      log.error("Not possible create product,{}", product);
      throw new SystemException("Not possible create product");
    }
  }

  @Override
  public List<ProductEntity> getAllProducts() {
    return productRepository.findAll();
  }
}
