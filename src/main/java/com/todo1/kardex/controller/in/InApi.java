package com.todo1.kardex.controller.in;

import com.todo1.kardex.commons.constants.api.in.EndpointIn;
import com.todo1.kardex.commons.exceptions.SystemException;
import com.todo1.kardex.model.dto.InDTO;
import com.todo1.kardex.service.in.IInService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
public class InApi {

  private final IInService inService;

  @Autowired
  public InApi(IInService inService) {
    this.inService = inService;
  }

  @PostMapping(EndpointIn.CREATE_IN)
  public ResponseEntity<InDTO> createIn(@RequestBody InDTO inDTO)
      throws SystemException, NotFoundException {
    var inResponse = inService.createIn(inDTO);

    return new ResponseEntity<>(inResponse, HttpStatus.CREATED);
  }
}
