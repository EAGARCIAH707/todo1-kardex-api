package com.todo1.kardex.controller.product;

import com.todo1.kardex.commons.constants.api.product.EndpointProduct;
import com.todo1.kardex.commons.exceptions.SystemException;
import com.todo1.kardex.model.dto.ProductDTO;
import com.todo1.kardex.model.entities.ProductEntity;
import com.todo1.kardex.service.product.IProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "")
@CrossOrigin(origins = "*")
@Log4j2
public class ProductApi {
  private final IProductService productService;

  @Autowired
  public ProductApi(IProductService productService) {
    this.productService = productService;
  }

  @PostMapping(EndpointProduct.CREATE_PRODUCT)
  public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO)
      throws SystemException {
    log.info("[POST] Create Product, {}", productDTO);
    var productResponse = productService.createProduct(productDTO);

    return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
  }

  @GetMapping(EndpointProduct.GET_PRODUCTS)
  public ResponseEntity<List<ProductEntity>> getAll() {
    log.info("[GET] Find All Products");
    var productsResponse = productService.getAllProducts();

    return ResponseEntity.ok(productsResponse);
  }
}
