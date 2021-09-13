package com.todo1.kardex.testdatabuilder;

import com.todo1.kardex.model.dto.ProductDTO;

import java.sql.Timestamp;

public class ProductDTODataBuilder {
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

    public ProductDTODataBuilder() {
        this.name = "name test";
        this.reference = "reference test";
        this.salePrice = 20000.0;
        this.purchasePrice = 30000.0;
        this.description = "description test";
        this.quantity = 10;
    }

    public ProductDTO productBuilder() {
        return new ProductDTO(productId, name, reference, salePrice, purchasePrice, description, state, available,
                createdOn, lastModified, quantity);
    }
}
