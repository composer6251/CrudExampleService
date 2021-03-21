package com.securegive.crudexample.service;

import com.securegive.crudexample.data.MockUsersData;
import com.securegive.crudexample.data.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class CrudExampleService {

    @Autowired
    MockUsersData mockUsersData;

    //Read
    public Optional<UserEntity> getUserById(int id){

        Optional<UserEntity> fetchedUser = getAllUsers().stream()
                .filter(u -> u.getId() == id)
                .findFirst();

        //todo: add condition for no user found
        return fetchedUser;
    }

    public List<UserEntity> getAllUsers(){
        return mockUsersData.getAllUsers();
    }

    public Optional<UserEntity> updateUser(){
        return null;
    }

    public String createUser(UserEntity userToCreate){


        if(nonNull(userToCreate.getId()) && nonNull(userToCreate.getFirstName()) && nonNull(userToCreate.getLastName())) {
            mockUsersData.createUser(userToCreate);
            return "Successfully created user " + userToCreate.getFirstName() + " " + userToCreate.getLastName();
        }
        return "Unable to create user. Id, first name, and last name are required";
    }

    public List<Optional<UserEntity>> deleteUser(int id){

        return null;
    }

    public Optional<UserEntity> updateUserAddress(int id, String address){

        Optional<UserEntity> userToUpdate = getUserById(id);

        userToUpdate.get().setAddress(address);

        return userToUpdate;
    }

    public Optional<UserEntity> updateUserFirstName(int id, String firstName){

        Optional<UserEntity> userToUpdate = getUserById(id);

        userToUpdate.get().setFirstName(firstName);

        return userToUpdate;
    }

    public Optional<UserEntity> updateUserLastName(int id, String lastName){

        Optional<UserEntity> userToUpdate = getUserById(id);

        userToUpdate.get().setLastName(lastName);

        return userToUpdate;
    }
}
