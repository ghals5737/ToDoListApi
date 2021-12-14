package com.example.back.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest
@AutoConfigureMockMvc
class ScheduleControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun getScheduleTest(){

    }
}