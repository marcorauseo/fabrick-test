package com.fabrick.test;

import com.fabrick.test.controller.ApiController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;



    @SpringBootTest
    public class SpringContextTest {

        @Autowired
        private ApiController apiController;

        @Test
        public void contextLoads() {

            assertNotNull(apiController);
        }
    }


