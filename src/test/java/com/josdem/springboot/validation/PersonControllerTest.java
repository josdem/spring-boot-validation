package com.josdem.springboot.validation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("saving person")
    void shouldSavePerson(TestInfo testInfo) throws Exception {
        log.info("Running: {}", testInfo.getDisplayName());
        mockMvc.perform(post("/persons")
                        .param("nickname", "josdem")
                        .param("email", "contact@josdem.io")
                        .param("ein", "123456789"))
                .andExpect(status().isOk())
                .andExpect(view().name("persons/list"));
    }

    @Test
    @DisplayName("not saving person with invalid email")
    void shouldNotSavePersonWithInvalidEmail(TestInfo testInfo) throws Exception {
        log.info("Running: {}", testInfo.getDisplayName());
        mockMvc.perform(post("/persons")
                        .param("nickname", "josdem")
                        .param("email", "contact")
                        .param("ein", "123456789"))
                .andExpect(status().isOk())
                .andExpect(view().name("persons/create"))
                .andExpect(model().attributeHasFieldErrors("personCommand", "email"));
    }

}

