package com.todo1.kardex.controller.kardex;

import com.todo1.kardex.commons.constants.api.kardex.EndpointKardex;
import com.todo1.kardex.model.dto.KardexDTO;
import com.todo1.kardex.model.entities.KardexEntity;
import com.todo1.kardex.service.kardex.IKardexService;
import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
@Log4j2
public class KardexApi {
  private final IKardexService kardexService;

  @Autowired
  public KardexApi(IKardexService kardexService) {
    this.kardexService = kardexService;
  }

  @GetMapping(EndpointKardex.FIND_KARDEX_BY_ID)
  public ResponseEntity<KardexDTO> getKardexById(@PathVariable("kardexId") Integer kardexId)
      throws NotFoundException {
    log.info("[GET] Find Kardex By Id, {}", kardexId);
    var kardexResponse = kardexService.findKardexById(kardexId);

    return ResponseEntity.ok(kardexResponse);
  }

  @GetMapping(EndpointKardex.FIND_ALL)
  public ResponseEntity<List<KardexEntity>> findAll() {
    log.info("[GET] Find All Kardex");
    var kardexResponse = kardexService.findAll();

    return ResponseEntity.ok(kardexResponse);
  }
}
