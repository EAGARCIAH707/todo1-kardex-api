package com.todo1.kardex.commons.exceptions;

import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice()
public class RestExceptionHandler {

  private static final ConcurrentHashMap<String, HttpStatus> STATUS_CODES =
      new ConcurrentHashMap<>();
  Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

  public RestExceptionHandler() {
    STATUS_CODES.put(NotFoundException.class.getSimpleName(), HttpStatus.NOT_FOUND);
    STATUS_CODES.put(SystemException.class.getSimpleName(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(value = {SystemException.class})
  public final ResponseEntity<GeneralErrorResponse> handleAllExceptions(
      SystemException ex, HttpServletRequest httpRequest) {
    ex.printStackTrace();

    return new ResponseEntity<>(
        GeneralErrorResponse.builder()
            .message(ex.getMessage())
            .path(httpRequest.getRequestURI())
            .build(),
        STATUS_CODES.getOrDefault(ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR));
  }

  @ExceptionHandler(value = {Exception.class})
  public final ResponseEntity<Object> handleAllExceptions(
      Exception ex, HttpServletRequest httpRequest) {
    ex.printStackTrace();

    return new ResponseEntity<>(
        GeneralErrorResponse.builder()
            .message(ex.getMessage())
            .path(httpRequest.getRequestURI())
            .build(),
        STATUS_CODES.getOrDefault(ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR));
  }

  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  public final ResponseEntity<Object> handleValidationFieldsException(
      MethodArgumentNotValidException ex, WebRequest request, HttpServletRequest httpRequest) {
    ex.printStackTrace();

    return new ResponseEntity<>(
        GeneralErrorResponse.builder().path(httpRequest.getRequestURI()).build(),
        HttpStatus.BAD_REQUEST);
  }
}
