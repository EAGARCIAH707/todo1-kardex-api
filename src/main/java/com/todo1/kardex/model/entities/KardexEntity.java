package com.todo1.kardex.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name = "kardex", schema = "public")
public class KardexEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id_kardex")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idKardex;

  @Basic
  @Column(name = "minimum_stock")
  private Integer minimumStock;

  @Basic
  @Column(name = "maximum_stock")
  private Integer maximumStock;

  @Basic
  @Column(name = "reference")
  private String reference;

  @Basic
  @Column(name = "unit_cost")
  private Double unitCost;

  @Basic
  @Column(name = "total_cost")
  private Double totalCost;

  @Basic
  @Column(name = "quantity")
  private Integer quantity;

  @Basic
  @Column(name = "available")
  private Boolean available;

  @Basic
  @Column(name = "created_on")
  private Date createdOn;

  @Basic
  @Column(name = "last_modified")
  private Date lastModified;

  @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
  @ManyToOne(optional = false)
  private ProductEntity productId;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "kardexId")
  private List<OutEntity> outList;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "kardexId")
  private List<InEntity> inList;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    KardexEntity that = (KardexEntity) o;
    return Objects.equals(idKardex, that.idKardex);
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
