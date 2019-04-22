package com.scut.se.sehubbackend.Controller;

import com.scut.se.sehubbackend.Others.Response;
import com.scut.se.sehubbackend.Service.ManagerService;
import com.scut.se.sehubbackend.UserDAORequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired ManagerService managerService;

    @PostMapping("/user")
    Response createUser(@RequestBody UserDAORequest userDAORequest){
        return managerService.createUser(userDAORequest);
    }

    @DeleteMapping("/user")
    Response deleteUser(@RequestBody UserDAORequest userDAORequest){
        return managerService.deleteUser(userDAORequest);
    }
}
