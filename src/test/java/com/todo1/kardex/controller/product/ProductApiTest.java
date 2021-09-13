package com.todo1.kardex.controller.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo1.kardex.auth.test.AuthTest;
import com.todo1.kardex.commons.constants.api.product.EndpointProduct;
import com.todo1.kardex.model.dto.ProductDTO;
import com.todo1.kardex.testdatabuilder.ProductDTODataBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class ProductApiTest {
  @Autowired private MockMvc mvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void createProduct() throws Exception {
    ProductDTO product = new ProductDTODataBuilder().productBuilder();

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(AuthTest.createToken());
    mvc.perform(
            MockMvcRequestBuilders.post(EndpointProduct.CREATE_PRODUCT)
                .content(objectMapper.writeValueAsString(product))
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  void createProductFail() throws Exception {
    ProductDTO product = new ProductDTODataBuilder().productBuilder();
    product.setQuantity(-3);
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(AuthTest.createToken());
    mvc.perform(
            MockMvcRequestBuilders.post(EndpointProduct.CREATE_PRODUCT)
                .content(objectMapper.writeValueAsString(product))
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isConflict());
  }

  @Test
  void getAll() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(AuthTest.createToken());
    mvc.perform(
            MockMvcRequestBuilders.get(EndpointProduct.GET_PRODUCTS)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
