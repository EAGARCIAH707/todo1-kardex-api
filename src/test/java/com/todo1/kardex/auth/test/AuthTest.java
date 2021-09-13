package com.todo1.kardex.auth.test;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class AuthTest {

  public static String createToken() {
    return Jwts.builder()
        .setSubject("usertest")
        .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 60))
        .signWith(SignatureAlgorithm.HS512, "test-secret")
        .compact();
  }
}
