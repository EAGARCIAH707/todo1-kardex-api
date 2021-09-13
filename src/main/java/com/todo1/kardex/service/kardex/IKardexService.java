package com.todo1.kardex.service.kardex;

import com.todo1.kardex.model.dto.KardexDTO;
import com.todo1.kardex.model.entities.KardexEntity;
import com.todo1.kardex.model.entities.ProductEntity;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface IKardexService {
  KardexDTO createKardexDTO(ProductEntity productEntity);

  Optional<KardexEntity> createKardex(KardexDTO kardexDTO);

  Optional<KardexEntity> createKardexFromProduct(ProductEntity productEntity);

  KardexDTO findKardexById(Integer kardexId) throws NotFoundException;

  KardexEntity findKardex(Integer kardexId) throws NotFoundException;

  List<KardexEntity> findAll();

  void updateKardex(KardexEntity kardexEntity);
}
