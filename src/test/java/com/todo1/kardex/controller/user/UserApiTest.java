package com.todo1.kardex.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo1.kardex.commons.constants.api.user.EndpointUser;
import com.todo1.kardex.model.dto.UserDto;
import com.todo1.kardex.model.dto.UserLoginDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class UserApiTest {

  @Autowired private MockMvc mvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void login() throws Exception {
    var login = UserLoginDTO.builder().email("usertest").password("123456789").build();
    mvc.perform(
            MockMvcRequestBuilders.post(EndpointUser.LOGIN)
                .content(objectMapper.writeValueAsString(login))
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNoContent());
  }

  @Test
  void singUp() throws Exception {
    var user =
        UserDto.builder()
            .email(UUID.randomUUID().toString())
            .password(UUID.randomUUID().toString())
            .build();
    mvc.perform(
            MockMvcRequestBuilders.post(EndpointUser.SIGN_UP)
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated());
  }
}
