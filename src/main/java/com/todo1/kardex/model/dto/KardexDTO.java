package com.todo1.kardex.model.dto;

import com.todo1.kardex.model.entities.InEntity;
import com.todo1.kardex.model.entities.OutEntity;
import com.todo1.kardex.model.entities.ProductEntity;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Builder
@ToString
public class KardexDTO {

  private Integer idKardex;

  private Integer quantity;

  private Integer minimumStock;

  private Integer maximumStock;

  private String reference;

  private Double unitCost;

  private Double totalCost;

  private ProductEntity productId;

  private List<OutEntity> outList;

  private List<InEntity> inList;
}
