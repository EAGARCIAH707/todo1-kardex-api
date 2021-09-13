package com.todo1.kardex.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@XmlRootElement
@EqualsAndHashCode
@Data
@Table(name = "role", schema = "public")
public class RoleEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "role_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer roleId;

  @Basic
  @Column(name = "role")
  private String role;

  @Basic
  @Column(name = "code_app")
  private String codeApp;

  @Basic
  @Column(name = "description")
  private String description;
}
