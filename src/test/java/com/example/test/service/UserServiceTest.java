package com.example.test.service;

import com.example.test.model.UserEntity;
import com.example.test.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepository repository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void create() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(0);
        userEntity.setFirstName("mickey");
        userEntity.setLastName("mouse");
        UserEntity save = repository.save(userEntity);

        log.info("create::save : {}", save);

        Assert.isTrue(userEntity.getFirstName().equals(save.getFirstName()));
    }

    @Test
    void findOne() {

        // when add a user
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setFirstName("mickey");
        userEntity.setLastName("mouse");
        UserEntity save = repository.save(userEntity);

        // then find
        UserEntity byId = repository.getById(1);

        System.out.println("byId.getId() = " + byId.getId());

        Assert.isTrue(1 == byId.getId());
    }

    @Test
    void find() {

        // when add a user
        UserEntity userEntity = new UserEntity();
        userEntity.setId(2);
        userEntity.setFirstName("pippo");
        userEntity.setLastName("pluto");
        UserEntity save = repository.save(userEntity);

        List<UserEntity> all = repository.findAll();
        Assert.isTrue(3 == all.size());
    }

    }
