package com.asm.apiexpress.controller;

import com.asm.apiexpress.api.UserApi;
import com.asm.apiexpress.pojo.Login;

import com.asm.apiexpress.service.JWTGenerate;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.service.Tags;

import javax.validation.Valid;

@RestController
@Api(tags = {"User"})
public class UserController implements UserApi {

    @Autowired
    JWTGenerate jwtGenerate;

    @Override
    public ResponseEntity<Void> userLoginPost(@Valid Login user) {
        String token = jwtGenerate.getToken(user.getUser());
        System.out.println(user.toString());
        System.out.printf("token::" + token);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
