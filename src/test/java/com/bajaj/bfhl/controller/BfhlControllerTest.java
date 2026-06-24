package com.bajaj.bfhl.controller;

import com.bajaj.bfhl.dto.BfhlRequest;
import com.bajaj.bfhl.dto.BfhlResponse;
import com.bajaj.bfhl.service.BfhlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BfhlController.class)
public class BfhlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BfhlService bfhlService;

    @Test
    public void testPostBfhl_Success() throws Exception {
        BfhlResponse mockResponse = new BfhlResponse(
                true,
                "diwanshu_baskota_16082003",
                "diwanshu1618.be23@chitkarauniversity.edu.in",
                "2311981618",
                Collections.singletonList("334"),
                Collections.singletonList("1"),
                Arrays.asList("A", "Y", "B"),
                Collections.singletonList("$"),
                "335",
                "ByA"
        );

        when(bfhlService.processData(any(BfhlRequest.class))).thenReturn(mockResponse);

        BfhlRequest request = new BfhlRequest(Arrays.asList("a", "1", "334", "$", "y", "b"));

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.user_id").value("diwanshu_baskota_16082003"))
                .andExpect(jsonPath("$.email").value("diwanshu1618.be23@chitkarauniversity.edu.in"))
                .andExpect(jsonPath("$.roll_number").value("2311981618"))
                .andExpect(jsonPath("$.even_numbers[0]").value("334"))
                .andExpect(jsonPath("$.odd_numbers[0]").value("1"))
                .andExpect(jsonPath("$.alphabets[0]").value("A"))
                .andExpect(jsonPath("$.special_characters[0]").value("$"))
                .andExpect(jsonPath("$.sum").value("335"))
                .andExpect(jsonPath("$.concat_string").value("ByA"));
    }

    @Test
    public void testPostBfhl_MalformedJson() throws Exception {
        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{invalid-json}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.is_success").value(false))
                .andExpect(jsonPath("$.error").exists());
    }
}
