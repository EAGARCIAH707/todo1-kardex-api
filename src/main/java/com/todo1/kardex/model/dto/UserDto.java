package com.todo1.kardex.model.dto;

import com.todo1.kardex.model.entities.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

  private Integer userId;

  private String idNumber;

  private String firstName;

  private String lastName;

  private String email;

  private String password;

  private Boolean userState;

  private Boolean state;

  private Timestamp createdOn;

  private Date lastModified;

  private RoleEntity roleId;
}
