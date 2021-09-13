package com.todo1.kardex.commons.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {

  private static final ObjectMapper mapper = new ObjectMapper();

  private Converter() {}

  public static <T> T converterObject(Object objet, Class<T> c) {
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper.convertValue(objet, c);
  }
}
