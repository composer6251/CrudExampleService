package com.securegive.crudexample.controller;

import com.securegive.crudexample.data.UserEntity;
import com.securegive.crudexample.dto.UserDTO;
import com.securegive.crudexample.service.CrudExampleService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CrudExampleController {

    @Autowired
    CrudExampleService crudExampleService;

    @GetMapping(value = "/get-user-by-id")
    public Optional<UserEntity> getUserById(@NonNull @RequestParam int id){

        Optional<UserEntity> user = crudExampleService.getUserById(id);
        return user;
    }

    @GetMapping(value = "/get-all-users")
    public List<UserEntity> getAllUsers(){

        return crudExampleService.getAllUsers();
    }

    @GetMapping(value = "/delete-user")
    public String deleteUser(@NonNull @RequestParam int id){

        return crudExampleService.deleteUser(id);
    }

    @GetMapping(value = "/update-user")
    public String updateUser(@RequestBody UserDTO userToUpdate){

        return crudExampleService.updateUser(userToUpdate);
    }
}
