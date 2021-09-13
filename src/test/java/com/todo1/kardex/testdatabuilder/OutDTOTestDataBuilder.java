package com.todo1.kardex.testdatabuilder;

import com.todo1.kardex.model.dto.OutDTO;

public class OutDTOTestDataBuilder {

  public OutDTO OutBuilder() {
    return OutDTO.builder().quantity(45).kardexId(12).unitValue(0.0).observation("test").build();
  }
}
