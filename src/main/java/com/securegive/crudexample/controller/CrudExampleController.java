package com.securegive.crudexample.controller;

import com.securegive.crudexample.data.UserEntity;
import com.securegive.crudexample.dto.UserDTO;
import com.securegive.crudexample.service.CrudExampleService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @DeleteMapping(value = "/delete-user")
    public String deleteUser(@NonNull @RequestParam int id){

        return crudExampleService.deleteUser(id);
    }

    @PostMapping(value = "/update-user")
    public String updateUser(@Valid @RequestBody UserDTO userToUpdate){

        return crudExampleService.updateUser(userToUpdate);
    }

    @PostMapping(value = "/create-user")
    public String createUser(@Valid @RequestBody UserDTO userToUpdate){

        return crudExampleService.createUser(userToUpdate);
    }
}
