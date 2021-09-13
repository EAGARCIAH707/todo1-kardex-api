package com.todo1.kardex.controller.out;

import com.todo1.kardex.commons.constants.api.out.EndpointOut;
import com.todo1.kardex.commons.exceptions.SystemException;
import com.todo1.kardex.model.dto.OutDTO;
import com.todo1.kardex.service.out.IOutService;
import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
@Log4j2
public class OutApi {
  private final IOutService outService;

  @Autowired
  public OutApi(IOutService outService) {
    this.outService = outService;
  }

  @PostMapping(EndpointOut.CREATE_OUT)
  public ResponseEntity<OutDTO> createOut(@RequestBody OutDTO outDTO)
      throws SystemException, NotFoundException {
    log.info("[POST] Create Out, {}", outDTO);
    var outResponse = outService.createOut(outDTO);

    return new ResponseEntity<>(outResponse, HttpStatus.CREATED);
  }
}
