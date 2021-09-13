package com.todo1.kardex.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@XmlRootElement
@Getter
@Setter
@ToString
@Table(name = "_user", schema = "public")
public class UserEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer userId;

  @Basic
  @Column(name = "id_number")
  private String idNumber;

  @Basic
  @Column(name = "first_name")
  private String firstName;

  @Basic
  @Column(name = "last_name")
  private String lastName;

  @Basic
  @Column(name = "email")
  private String email;

  @Basic
  @Column(name = "password")
  private String password;

  @Basic
  @Column(name = "user_state")
  private Boolean userState;

  @Basic
  @Column(name = "available")
  private Boolean available;

  @Basic
  @Column(name = "created_on")
  private java.util.Date createdOn;

  @Basic
  @Column(name = "last_modified")
  private Date lastModified;

  @JsonIgnore
  @JoinColumn(name = "role_id", referencedColumnName = "role_id")
  @ManyToOne
  private RoleEntity roleId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    UserEntity that = (UserEntity) o;
    return Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @PrePersist
  private void prePersist() {
    this.available = true;
    this.createdOn = new Date();
    this.lastModified = new Date();
  }
}
