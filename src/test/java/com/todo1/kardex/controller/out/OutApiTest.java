package com.todo1.kardex.controller.out;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo1.kardex.auth.test.AuthTest;
import com.todo1.kardex.commons.constants.api.out.EndpointOut;
import com.todo1.kardex.model.dto.OutDTO;
import com.todo1.kardex.testdatabuilder.OutDTOTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class OutApiTest {

  @Autowired private MockMvc mvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void createOut() throws Exception {
    OutDTO outDTO = new OutDTOTestDataBuilder().OutBuilder();
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(AuthTest.createToken());
    mvc.perform(
            MockMvcRequestBuilders.post(EndpointOut.CREATE_OUT)
                .content(objectMapper.writeValueAsString(outDTO))
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  void createOutFail() throws Exception {
    OutDTO outDTO = new OutDTOTestDataBuilder().OutBuilder();
    outDTO.setQuantity(100000);
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(AuthTest.createToken());
    mvc.perform(
            MockMvcRequestBuilders.post(EndpointOut.CREATE_OUT)
                .content(objectMapper.writeValueAsString(outDTO))
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isConflict());
  }
}
