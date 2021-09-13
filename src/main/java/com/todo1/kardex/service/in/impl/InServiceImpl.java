package com.todo1.kardex.service.in.impl;

import com.todo1.kardex.commons.exceptions.SystemException;
import com.todo1.kardex.model.dto.InDTO;
import com.todo1.kardex.model.entities.InEntity;
import com.todo1.kardex.model.entities.KardexEntity;
import com.todo1.kardex.repository.in.impl.InRepositoryFacade;
import com.todo1.kardex.service.in.IInService;
import com.todo1.kardex.service.kardex.IKardexService;
import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.todo1.kardex.commons.util.Converter.converterObject;

@Service
@Log4j2
public class InServiceImpl implements IInService {

  private final InRepositoryFacade inRepository;

  private final IKardexService kardexService;

  @Autowired
  public InServiceImpl(InRepositoryFacade inRepository, IKardexService kardexService) {
    this.inRepository = inRepository;
    this.kardexService = kardexService;
  }

  @Override
  public InDTO createIn(InDTO inDTO) throws SystemException, NotFoundException {
    log.info("create in,{}", inDTO);
    var kardex = kardexService.findKardex(inDTO.getKardexId());
    var in = inRepository.save(converterObject(calculateValues(inDTO, kardex), InEntity.class));
    if (in.isPresent()) {
      return converterObject(in.get(), InDTO.class);
    } else {
      log.error("Not possible create In");
      throw new SystemException("Not possible create In");
    }
  }

  public InDTO calculateValues(InDTO inDTO, KardexEntity kardex) {
    log.info("calculating values and updating kardex,{},{}", inDTO, kardex);
    Double unitCost =
        inDTO.getUnitValue() > 0
            ? inDTO.getUnitValue()
            : (Math.round(kardex.getUnitCost() * 100.0) / 100.0);
    inDTO.setUnitValue(unitCost);
    inDTO.setTotalValue(inDTO.getQuantity() * unitCost);

    kardex.setQuantity(kardex.getQuantity() + inDTO.getQuantity());
    kardex.setTotalCost(kardex.getTotalCost() + inDTO.getTotalValue());
    kardex.setUnitCost(
        kardex.getTotalCost() / (kardex.getQuantity() == 0 ? 1 : kardex.getQuantity()));

    inDTO.setKQuantity(kardex.getQuantity());
    inDTO.setKTotalValue(kardex.getTotalCost());
    inDTO.setKUnitValue(kardex.getUnitCost());

    kardexService.updateKardex(kardex);
    return inDTO;
  }

  @Override
  public void createInFromKardex(KardexEntity kardexEntity) throws SystemException {
    if (kardexEntity.getQuantity() > 0) {
      save(converterObject(kardexEntity, InEntity.class));
    } else {
      log.error("It is not possible to create a product with a negative amount");
      throw new SystemException("It is not possible to create a product with a negative amount");
    }
  }

  @Override
  public void save(InEntity inEntity) {
    inRepository.save(inEntity);
  }
}
