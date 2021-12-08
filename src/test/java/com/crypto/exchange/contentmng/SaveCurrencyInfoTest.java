package com.crypto.exchange.contentmng;

import com.crypto.exchange.contentmng.dto.request.CoinInfoAddRequest;
import com.crypto.exchange.contentmng.dto.request.MetaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gd.auth.test.Oauth2MockUser;
import org.junit.jupiter.api.Assertions;
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
class SaveCurrencyInfoTest {

    private final ObjectMapper objectMapper;
    private final MockMvc mockMvc;

    @Autowired
    public SaveCurrencyInfoTest(ObjectMapper objectMapper, MockMvc mockMvc) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
    }

    @Test
    void save() throws Exception {
        CoinInfoAddRequest coinInfoAddRequest = CoinInfoAddRequest.builder()
                .coinId(1)
                .description("adasdsadadasdsadadasdsadadasdsadadasdsadadasdsadadasdsad")
                .metas(Collections.singletonList("asdasd"))
                .path("adasdsadadasdsad")
                .tags(Arrays.asList("ش", "ش", "ش"))
                .title("adasdsadadasdsadadasdsad")
                .lang("asd")
                .metas(Collections.singletonList("sadasd"))
                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(UriComponentsBuilder.fromUriString("/coinInfo").build().toUri())
                .content(objectMapper.writeValueAsString(coinInfoAddRequest))
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        assert mvcResult.getResponse().getStatus() == HttpStatus.OK.value();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
}
