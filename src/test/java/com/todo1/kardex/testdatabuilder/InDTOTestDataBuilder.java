package com.todo1.kardex.testdatabuilder;

import com.todo1.kardex.model.dto.InDTO;

public class InDTOTestDataBuilder {

  public InDTO inBuilder() {
    return InDTO.builder().quantity(45).kardexId(12).observation("test").unitValue(0.0).build();
  }
}
