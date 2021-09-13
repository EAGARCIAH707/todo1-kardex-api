package com.todo1.kardex.service.out.impl;

import com.todo1.kardex.commons.exceptions.SystemException;
import com.todo1.kardex.model.dto.OutDTO;
import com.todo1.kardex.model.entities.KardexEntity;
import com.todo1.kardex.model.entities.OutEntity;
import com.todo1.kardex.repository.out.impl.OutRepositoryFacade;
import com.todo1.kardex.service.kardex.IKardexService;
import com.todo1.kardex.service.out.IOutService;
import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.todo1.kardex.commons.util.Converter.converterObject;

@Service
@Log4j2
public class OutServiceImpl implements IOutService {

  private final OutRepositoryFacade outRepository;

  private final IKardexService kardexService;

  @Autowired
  public OutServiceImpl(OutRepositoryFacade outRepository, IKardexService kardexService) {
    this.outRepository = outRepository;
    this.kardexService = kardexService;
  }

  @Override
  public OutDTO createOut(OutDTO outDTO) throws SystemException, NotFoundException {
    var kardex = kardexService.findKardex(outDTO.getKardexId());
    if (kardex.getQuantity() < outDTO.getQuantity()) {
      log.error("The value for the output is higher than the stock,{}", kardex.getQuantity());
      throw new SystemException("The value for the output is higher than the stock");
    }
    var out = outRepository.save(converterObject(calculateValues(outDTO, kardex), OutEntity.class));
    if (out.isPresent()) {
      return converterObject(out.get(), OutDTO.class);
    } else {
      log.error("Not possible create Out");
      throw new SystemException("Not possible create Out");
    }
  }

  public OutDTO calculateValues(OutDTO outDTO, KardexEntity kardex) {
    log.info("calculating values and updating kardex,{},{}", outDTO, kardex);
    Double unitCost =
        outDTO.getUnitValue() > 0
            ? outDTO.getUnitValue()
            : (Math.round(kardex.getUnitCost() * 100.0) / 100.0);
    outDTO.setUnitValue(unitCost);
    outDTO.setTotalValue(outDTO.getQuantity() * unitCost);

    kardex.setQuantity(kardex.getQuantity() - outDTO.getQuantity());
    kardex.setTotalCost(kardex.getTotalCost() - outDTO.getTotalValue());
    kardex.setUnitCost(
        kardex.getTotalCost() / (kardex.getQuantity() == 0 ? 1 : kardex.getQuantity()));

    outDTO.setKQuantity(kardex.getQuantity());
    outDTO.setKTotalValue(kardex.getTotalCost());
    outDTO.setKUnitValue(kardex.getUnitCost());
    kardexService.updateKardex(kardex);
    return outDTO;
  }
}
