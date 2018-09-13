package com.n26.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 */
@AutoConfigureMockMvc
public class RestControllerHelper {

    @Autowired
    private MockMvc mockMvc;

    protected ResultActions getMethod(String url, Object... urlVariables) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(url, urlVariables).accept(MediaType.APPLICATION_JSON_VALUE));
    }

    protected ResultActions postMethod(String url, String content, Object... urlVariables) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(url, urlVariables).accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(content));
    }
}