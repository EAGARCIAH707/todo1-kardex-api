package com.todo1.kardex.commons.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GeneralErrorResponse {
  private String errorCode;
  private LocalDateTime timeResponse;
  private String message;
  private String path;
}
