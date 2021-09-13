package com.todo1.kardex.controller.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo1.kardex.auth.test.AuthTest;
import com.todo1.kardex.commons.constants.api.in.EndpointIn;
import com.todo1.kardex.model.dto.InDTO;
import com.todo1.kardex.testdatabuilder.InDTOTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
class InApiTest {

  @Autowired private MockMvc mvc;

  @Autowired private ObjectMapper objectMapper;

  @Value("${server.servlet.context-path}")
  private String basePath;

  @Test
  void createIn() throws Exception {
    InDTO inDTO = new InDTOTestDataBuilder().inBuilder();
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(AuthTest.createToken());
    mvc.perform(
            MockMvcRequestBuilders.post(EndpointIn.CREATE_IN)
                .content(objectMapper.writeValueAsString(inDTO))
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated());
  }
}
