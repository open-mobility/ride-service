package com.o4.mobility.test;

import com.o4.mobility.controllers.WelcomeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WelcomeController.class) // Loads only the web layer
public class TestWelcomeController {
    @Autowired
    private MockMvc mockMvc; // Provides MockMvc for performing requests

    @Test
    void testWebLayer() throws Exception {
        mockMvc.perform(get("/web-layer"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("status", is("OK")))
                .andExpect(cookie().exists("Custom-Cookie"))
                .andExpect(cookie().value("Custom-Cookie", "I am the Cookie"))
                .andExpect(header().string("Custom-Header", "I am the Header"))
        ;
    }

    @Test
    public void testRedirection() throws Exception {
        mockMvc.perform(get("/redirect"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/hello"));
    }
}
