package com.securegive.crudexample.data;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MockUsersData {

    private static List<UserEntity> users = new ArrayList<>();

    static{
        users.add(new UserEntity(1, "John", "user1", "100 Test Avenue"));
        users.add(new UserEntity(2, "Doe", "user2", "200 Test Avenue"));
        users.add(new UserEntity(3, "David", "user3", "300 Test Avenue"));
    }

    public List<UserEntity> getAllUsers(){

        return this.users;
    }
}
