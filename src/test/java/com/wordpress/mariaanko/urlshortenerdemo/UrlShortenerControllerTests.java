package com.wordpress.mariaanko.urlshortenerdemo;

import com.jayway.jsonpath.JsonPath;
import com.wordpress.mariaanko.urlshortenerdemo.controller.UrlShortenerController;
import com.wordpress.mariaanko.urlshortenerdemo.repository.UrlShortenerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlShortenerControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UrlShortenerRepository urlShortenerRepository;

    @Test
    public void createAShortenedUrl() throws Exception {
        mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"originalUrl\": \"testerska-url.sk\" }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void getAShortenedUrl() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"originalUrl\": \"testerska-url.sk\" }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
        String generatedUrl = JsonPath.read(result.getResponse().getContentAsString(), "$.generatedUrl");
        mockMvc.perform(get("/api/get/" + generatedUrl).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getAShortenedUrlNoContent() throws Exception {
        mockMvc.perform(get("/api/get/" + "testerska-url.sk").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
