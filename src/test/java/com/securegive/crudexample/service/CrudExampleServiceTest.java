package com.securegive.crudexample.service;


import com.securegive.crudexample.data.MockUsersData;
import com.securegive.crudexample.data.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CrudExampleServiceTest {

    @Spy
    private MockUsersData mockUsersData;

    @InjectMocks
    private CrudExampleService crudExampleService;

    @Test
    public void errorThrownWhenUserNotFound(){

    }

    @Test
    public void getAllUsersReturnsCorrectSize(){
        List<UserEntity> actualUsers = crudExampleService.getAllUsers();
        List<UserEntity> expectedUsers = mockUsersData.getAllUsers();

        Assert.assertEquals(expectedUsers.size(), actualUsers.size());

    }

    @Test
    public void getUserByIdReturnsCorrectUser(){
        Optional<UserEntity> actualUser = crudExampleService.getUserById(1);

        Assert.assertEquals(actualUser.get().getId(), 1);
        Assert.assertEquals(actualUser.get().getFirstName(), "test");
        Assert.assertEquals(actualUser.get().getLastName(), "user1");
        Assert.assertEquals(actualUser.get().getAddress(), "100 Test Avenue");

    }

    @Test
    public void createUserAddsToUserList(){
        String userCreatedMessage = crudExampleService.createUser(new UserEntity(4, "test", "user4", "400 Test Avenue"));

        Assert.assertEquals(userCreatedMessage, "Successfully created user test user4" );
    }

    @Test
    public void deleteUserRemovesFromUserList(){
        String userDeletedMessage = crudExampleService.deleteUser(3);

        Assert.assertEquals(userDeletedMessage, "User test user3 has been deleted");
    }

    @Test
    public void deleteUserNotFoundReturnsErrorMessage(){
        String userNotDeletedMessage = crudExampleService.deleteUser(5);

        Assert.assertEquals(userNotDeletedMessage, "User not found to delete");
    }

    @Test
    public void createUserReturnsErrorMessageNullFirstName(){
        String userCreatedMessage = crudExampleService.createUser(new UserEntity(4, null, "user4", "400 Test Avenue"));

        Assert.assertEquals(userCreatedMessage, "Unable to create user. Id, first name, and last name are required" );
    }

    @Test
    public void createUserReturnsErrorMessageNullLastName(){
        String userCreatedMessage = crudExampleService.createUser(new UserEntity(4, "testUser", null, "400 Test Avenue"));

        Assert.assertEquals(userCreatedMessage, "Unable to create user. Id, first name, and last name are required" );
    }

    @Test
    public void updateUserDoesNotAlterListSize(){
        int listSizeBeforeUpdate = crudExampleService.getAllUsers().size();

        Optional<UserEntity> updateUser = crudExampleService.updateUser(new UserEntity(3, "test", "user3", "updated address"));

        int listSizeAfterUpdate = crudExampleService.getAllUsers().size();

        Assert.assertEquals(listSizeBeforeUpdate, listSizeAfterUpdate);
    }


}
