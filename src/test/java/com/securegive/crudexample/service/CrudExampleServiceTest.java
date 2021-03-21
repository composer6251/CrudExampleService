package com.securegive.crudexample.service;


import com.securegive.crudexample.data.MockUsersData;
import com.securegive.crudexample.data.UserEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;


public class CrudExampleServiceTest {

    @Mock
    MockUsersData mockUsersData;

    @InjectMocks
    CrudExampleService crudExampleService;

    @Test
    public void errorThrownWhenUserNotFound(){

    }

    @Test
    public void getAllUsersReturnsAllUsers(){
        List<UserEntity> fetchedUsers = crudExampleService.getAllUsers();

    }
}
