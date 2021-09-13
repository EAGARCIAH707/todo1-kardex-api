package com.todo1.kardex.model.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Builder
@ToString
public class InDTO {

  private Integer inId;

  private Integer quantity;

  private Timestamp lastModified;

  private Integer kardexId;

  private String observation;

  private Double unitValue;

  private Double totalValue;

  private Timestamp date;

  private Double kUnitValue;

  private Double kTotalValue;

  private Integer kQuantity;

  private Integer type;
}
