package com.todo1.kardex.service.kardex.impl;

import com.todo1.kardex.commons.util.MergeObjects;
import com.todo1.kardex.model.dto.KardexDTO;
import com.todo1.kardex.model.entities.KardexEntity;
import com.todo1.kardex.model.entities.ProductEntity;
import com.todo1.kardex.repository.kardex.impl.KardexRepositoryFacade;
import com.todo1.kardex.service.kardex.IKardexService;
import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.todo1.kardex.commons.util.Converter.converterObject;

@Service
@Log4j2
public class KardexServiceImpl implements IKardexService {

  private final KardexRepositoryFacade kardexRepository;

  @Autowired
  public KardexServiceImpl(KardexRepositoryFacade kardexRepositoryFacade) {
    this.kardexRepository = kardexRepositoryFacade;
  }

  @Override
  public KardexDTO createKardexDTO(ProductEntity productEntity) {
    log.info("Create kardex of product,{}", productEntity);
    var kardexDto = converterObject(productEntity, KardexDTO.class);
    kardexDto.setProductId(productEntity);
    return kardexDto;
  }

  @Override
  public Optional<KardexEntity> createKardex(KardexDTO kardexDTO) {
    log.info("Create kardex,{}", kardexDTO);
    return kardexRepository.save(converterObject(kardexDTO, KardexEntity.class));
  }

  @Override
  public Optional<KardexEntity> createKardexFromProduct(ProductEntity productEntity) {
    var kardexDto = new KardexDTO();
    MergeObjects.mergeObjects(productEntity, kardexDto);
    kardexDto.setProductId(productEntity);
    kardexDto.setMaximumStock(0);
    kardexDto.setMaximumStock(0);
    kardexDto.setUnitCost(productEntity.getPurchasePrice());
    kardexDto.setTotalCost(productEntity.getPurchasePrice() * productEntity.getQuantity());
    return createKardex(kardexDto);
  }

  @Override
  public KardexDTO findKardexById(Integer kardexId) throws NotFoundException {
    var kardex = kardexRepository.findById(kardexId);
    if (kardex.isPresent()) {
      return converterObject(kardex.get(), KardexDTO.class);
    } else {
      log.error("Kardex not found,{}", kardexId);
      throw new NotFoundException("resource not found <KardexEntity>");
    }
  }

  @Override
  public KardexEntity findKardex(Integer kardexId) throws NotFoundException {
    var kardex = kardexRepository.findById(kardexId);
    if (kardex.isPresent()) {
      return kardex.get();
    } else {
      log.error("Kardex not found,{}", kardexId);
      throw new NotFoundException("Not found Kardex");
    }
  }

  @Override
  public List<KardexEntity> findAll() {
    return kardexRepository.findAll();
  }

  @Override
  public void updateKardex(KardexEntity kardexEntity) {
    log.error("Save kardex,{}", kardexEntity);
    kardexRepository.save(kardexEntity);
  }
}
