package com.todo1.kardex.controller.kardex;

import com.todo1.kardex.auth.test.AuthTest;
import com.todo1.kardex.commons.constants.api.kardex.EndpointKardex;
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
class KardexApiTest {

  @Autowired private MockMvc mvc;

  @Value("${server.servlet.context-path}")
  private String basePath;

  @Test
  void getKardexById() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(AuthTest.createToken());
    mvc.perform(
            MockMvcRequestBuilders.get("/kardex/12")
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void findAll() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(AuthTest.createToken());
    mvc.perform(
            MockMvcRequestBuilders.get(EndpointKardex.FIND_ALL)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
