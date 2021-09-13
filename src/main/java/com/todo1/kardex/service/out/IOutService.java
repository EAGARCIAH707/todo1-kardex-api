package com.todo1.kardex.service.out;

import com.todo1.kardex.commons.exceptions.SystemException;
import com.todo1.kardex.model.dto.OutDTO;
import com.todo1.kardex.model.entities.KardexEntity;
import javassist.NotFoundException;

public interface IOutService {
  OutDTO createOut(OutDTO outDTO) throws SystemException, NotFoundException;

  OutDTO calculateValues(OutDTO outDTO, KardexEntity kardex);
}
