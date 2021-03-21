package com.securegive.crudexample.data;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MockUsersData {

    private static List<UserEntity> users = new ArrayList<>();

    static{
        users.add(new UserEntity(1, "test", "user1", "100 Test Avenue"));
        users.add(new UserEntity(2, "test", "user2", "200 Test Avenue"));
        users.add(new UserEntity(3, "test", "user3", "300 Test Avenue"));
    }
    //Read
    public List<UserEntity> getAllUsers(){

        return this.users;

    }
//    public String createUser(UserEntity userToCreate){
//
//        if(nonNull(userToCreate.getId()) && nonNull(userToCreate.getFirstName()) && nonNull(userToCreate.getLastName())) {
//            users.add(userToCreate);
//            return "Successfully created user " + userToCreate.getFirstName() + " " + userToCreate.getLastName();
//        }
//        else {
//            return "Unable to create user. Id, first name, and last name are required";
//        }
//    }
}
