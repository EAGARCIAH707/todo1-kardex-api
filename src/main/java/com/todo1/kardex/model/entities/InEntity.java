package com.todo1.kardex.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@XmlRootElement
@EqualsAndHashCode
@Data
@Table(name = "_in")
public class InEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "in_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer inId;

  @Basic
  @Column(name = "quantity")
  private Integer quantity;

  @Basic
  @Column(name = "type")
  private Integer type;

  @Basic
  @Column(name = "k_unit_value")
  private Double kUnitValue;

  @Basic
  @Column(name = "k_quantity")
  private Integer kQuantity;

  @Basic
  @Column(name = "k_total_value")
  private Double kTotalValue;

  @Basic
  @Column(name = "observation")
  private String observation;

  @Basic
  @Column(name = "unit_value")
  private Double unitValue;

  @Basic
  @Column(name = "total_value")
  private Double totalValue;

  @Basic
  @Column(name = "date")
  private Timestamp date;

  private Integer kardexId;

  @Basic
  @Column(name = "available")
  private Boolean available;

  @Basic
  @Column(name = "created_on")
  private Date createdOn;

  @Basic
  @Column(name = "last_modified")
  private Date lastModified;

  @PrePersist
  private void prePersist() {
    this.available = true;
    this.createdOn = new Date();
    this.lastModified = new Date();
  }
}
