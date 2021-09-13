package com.todo1.kardex.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@Table(name = "out", schema = "public")
public class OutEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "out_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer outId;

  @Basic
  @Column(name = "quantity")
  private Integer quantity;

  @Basic
  @Column(name = "type")
  private Integer type;

  @Basic
  @Column(name = "unit_value")
  private Double unitValue;

  @Basic
  @Column(name = "observation")
  private String observation;

  @Basic
  @Column(name = "total_value")
  private Double totalValue;

  @Basic
  @Column(name = "k_unit_value")
  private Double kUnitValue;

  @Basic
  @Column(name = "k_total_value")
  private Double kTotalValue;

  @Basic
  @Column(name = "k_quantity")
  private Integer kQuantity;

  @Basic
  @Column(name = "available")
  private Boolean available;

  @Basic
  @Column(name = "created_on")
  private Date createdOn;

  @Basic
  @Column(name = "last_modified")
  private Date lastModified;

  private Integer kardexId;

  @PrePersist
  private void prePersist() {
    this.available = true;
    this.createdOn = new Date();
    this.lastModified = new Date();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    OutEntity outEntity = (OutEntity) o;
    return Objects.equals(outId, outEntity.outId);
  }

  @Override
  public int hashCode() {
    return 0;
  }
}
