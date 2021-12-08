package com.crypto.exchange.contentmng;

import com.crypto.exchange.contentmng.dto.request.FaqModifyRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gd.auth.test.Oauth2MockUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest
@AutoConfigureMockMvc
@Oauth2MockUser(username = "admin", authorities = {"ROLE_ADMIN", "ROLE_AUTHOR"}, userId = 1)
@DirtiesContext
class FaqModifyTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public FaqModifyTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    @DisplayName("modify Unavailable")
    void modifyUnavailable() throws Exception {
        FaqModifyRequest faqModifyRequest = FaqModifyRequest.builder()
                .answer("asdasasdasdsadsadsadsadsadasdd")
                .id(100)
                .question("asadasdasddasdasdasdasdsad")
                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .put(UriComponentsBuilder.fromUriString("/faqs").build().toUri())
                .content(objectMapper.writeValueAsBytes(faqModifyRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assertions.assertEquals(HttpStatus.CONFLICT.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName("modify Question min size")
    void modifyQuestionSize() throws Exception {
        FaqModifyRequest faqModifyRequest = FaqModifyRequest.builder()
                .answer("asdasasdasdsadsadsadsadsadasdd")
                .id(100)
                .question("")
                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .put(UriComponentsBuilder.fromUriString("/faqs").build().toUri())
                .content(objectMapper.writeValueAsBytes(faqModifyRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName("modify Answer min size")
    void modifyAnswerSize() throws Exception {
        FaqModifyRequest faqModifyRequest = FaqModifyRequest.builder()
                .answer("")
                .id(100)
                .question("sadasdasdasdadasdasdasdaasdasdas")
                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .put(UriComponentsBuilder.fromUriString("/faqs").build().toUri())
                .content(objectMapper.writeValueAsBytes(faqModifyRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
    }
}
