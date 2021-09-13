package com.todo1.kardex.service.security;

public interface ISecurityService {
  String authenticate(String email, String password);
}
