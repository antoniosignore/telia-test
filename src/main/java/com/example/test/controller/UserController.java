package com.example.test.controller;

import com.example.test.beans.UserValueBean;
import com.example.test.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping (path = "/users")
    public ResponseEntity<UserValueBean> createUser (@RequestBody UserValueBean userValueBean){

        log.info("UserController.createUser");

        UserValueBean user = userService.create(userValueBean);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<UserValueBean>> getAllUsers (){
        log.info("UserController.getAllUsers");

        List<UserValueBean> all = userService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserValueBean> getUser (@PathVariable int id){

        log.info("UserController.getUser");
        log.info("getUser::id : {}", id);

        UserValueBean userValueBean = userService.find(id);
        if (userValueBean != null) {
            return new ResponseEntity<>(userValueBean, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

}
