package com.crypto.exchange.contentmng;

import com.crypto.exchange.contentmng.dto.request.CoinInfoAddRequest;
import com.crypto.exchange.contentmng.dto.request.FaqAddRequest;
import com.crypto.exchange.contentmng.dto.request.FaqCoinAddRequest;
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

import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
@Oauth2MockUser(username = "admin", authorities = {"ROLE_ADMIN", "ROLE_AUTHOR"}, userId = 1)
@DirtiesContext
class FaqSaveTest {

    private final ObjectMapper objectMapper;
    private final MockMvc mockMvc;

    @Autowired
    public FaqSaveTest(ObjectMapper objectMapper, MockMvc mockMvc) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("normal save")
    void save() throws Exception {
        saveCoinInfo();
        FaqAddRequest faqAddRequest = FaqAddRequest.builder()
                .answer("sadasdasdadasdsadasdasdsadsadasd")
                .question("sadasdsadsadsadsadsadasddsadsad")
                .build();
        FaqCoinAddRequest faqCoinAddRequest = FaqCoinAddRequest.builder()
                .coinId(1)
                .faqs(Collections.singletonList(faqAddRequest))
                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(UriComponentsBuilder.fromUriString("/faqs").build().toUri())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(faqCoinAddRequest))).andReturn();

        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName("save by null faq")
    void saveNullFaq() throws Exception {
        FaqCoinAddRequest faqCoinAddRequest = FaqCoinAddRequest.builder()
                .coinId(1)
                .faqs(null)
                .build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(UriComponentsBuilder.fromUriString("/faqs").build().toUri())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(faqCoinAddRequest))).andReturn();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName("save by not code")
    void saveNotCode() throws Exception {
        FaqAddRequest faqAddRequest = FaqAddRequest.builder()
                .answer("sadasdasdadasdsadasdasdsadsadasd")
                .question("sadasdsadsadsadsadsadasddsadsad")
                .build();
        FaqCoinAddRequest faqCoinAddRequest = FaqCoinAddRequest.builder()
                .coinId(100)
                .faqs(Collections.singletonList(faqAddRequest))
                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(UriComponentsBuilder.fromUriString("/faqs").build().toUri())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(faqCoinAddRequest))).andReturn();

        Assertions.assertEquals(HttpStatus.CONFLICT.value(), mvcResult.getResponse().getStatus());
    }

    void saveCoinInfo() throws Exception {
        CoinInfoAddRequest coinInfoAddRequest = CoinInfoAddRequest.builder()
                .coinId(1)
                .description("adasdsadadasdsadadasdsadadasdsadadasdsadadasdsadadasdsad")
                .metas(Collections.singletonList("asd"))
                .path("adasdsadadasdsad")
                .tags(Arrays.asList("ش", "ش", "ش"))
                .lang("as")
                .title("adasdsadadasdsadadasdsad")
                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(UriComponentsBuilder.fromUriString("/coinInfo").build().toUri())
                .content(objectMapper.writeValueAsString(coinInfoAddRequest))
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
}
