package com.todo1.kardex.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginDTO {

  private String email;

  private String password;
}
