package com.todo1.kardex.commons.exceptions;

public class SystemException extends Exception {

  public SystemException(String message) {
    super(message);
  }

  public SystemException(String s, Throwable throwable) {
    super(s, throwable);
  }
}
