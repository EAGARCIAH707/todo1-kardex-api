package com.todo1.kardex.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

  private Integer productId;

  private String name;

  private String reference;

  private Double salePrice;

  private Double purchasePrice;

  private String description;

  private Boolean state;

  private Boolean available;

  private Timestamp createdOn;

  private Timestamp lastModified;

  private Integer quantity;
}
