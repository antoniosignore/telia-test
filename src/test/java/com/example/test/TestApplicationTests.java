package com.example.test;

import com.example.test.controller.UserController;
import com.example.test.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class TestApplicationTests {


    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        Assert.notNull(userController);
        Assert.notNull(userService);
    }

}
