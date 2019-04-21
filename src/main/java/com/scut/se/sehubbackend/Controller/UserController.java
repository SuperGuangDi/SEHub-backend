package com.scut.se.sehubbackend.Controller;

import com.scut.se.sehubbackend.Others.Response;
import com.scut.se.sehubbackend.Service.AuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired private AuthorizeService authorizeService;


    @PostMapping("/login")
    public Response login(){
        return authorizeService.login();
    }
}
