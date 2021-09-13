package com.todo1.kardex.service.in;

import com.todo1.kardex.commons.exceptions.SystemException;
import com.todo1.kardex.model.dto.InDTO;
import com.todo1.kardex.model.entities.InEntity;
import com.todo1.kardex.model.entities.KardexEntity;
import javassist.NotFoundException;

public interface IInService {
  InDTO createIn(InDTO inDTO) throws SystemException, NotFoundException;

  InDTO calculateValues(InDTO inDTO, KardexEntity kardex);

  void createInFromKardex(KardexEntity kardexEntity) throws SystemException;

  void save(InEntity inEntity);
}
