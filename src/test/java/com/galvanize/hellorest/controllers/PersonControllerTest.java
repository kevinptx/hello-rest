package com.galvanize.hellorest.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {
    @Autowired
    MockMvc mvc;
    @Test
    public void createPerson() throws Exception{
        String json = "{\"name\":\"Rob\",\"birthDate\":\"1962-11-16\",\"email\":\"rob.wing@galvanize.com\"}";
        mvc.perform(post("/api/person")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("rob.wing@galvanize.com")))
                .andExpect(jsonPath("$.age").value(57));
    }




    //Method to create a date in the past for testing
    private Date getTestDob(int years){
        LocalDate ld = LocalDate.now();
        ld.minusYears(1l);

        Calendar ci = Calendar.getInstance();
        ci.add(Calendar.YEAR, -years);
        return ci.getTime();
    }


}