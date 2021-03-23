package com.securegive.crudexample.service;


import com.securegive.crudexample.data.MockUsersData;
import com.securegive.crudexample.data.UserEntity;
import com.securegive.crudexample.dto.UserDTO;
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
    public void getAllUsersReturnsCorrectSize(){
        List<UserEntity> actualUsers = crudExampleService.getAllUsers();
        List<UserEntity> expectedUsers = mockUsersData.getAllUsers();

        Assert.assertEquals(expectedUsers.size(), actualUsers.size());

    }

    @Test
    public void getUserByIdReturnsCorrectUser(){
        Optional<UserEntity> actualUser = crudExampleService.getUserById(1);

        Assert.assertEquals(actualUser.get().getId(), 1);
        Assert.assertEquals(actualUser.get().getFirstName(), "John");
        Assert.assertEquals(actualUser.get().getLastName(), "user1");
        Assert.assertEquals(actualUser.get().getAddress(), "100 Test Avenue");

    }

    @Test
    public void createUserAddsToUserList(){
        String userCreatedMessage = crudExampleService.createUser(new UserDTO(4, "test", "user4", "400 Test Avenue"));

        Assert.assertEquals(userCreatedMessage, "Successfully created user test user4" );
    }

    @Test
    public void createUserBlankFirstNameReturnsNoUpdate(){
        UserDTO userToUpdate = new UserDTO(3, " ", "Created User", "Created Address");

        String userCreatedMessage = crudExampleService.createUser(userToUpdate);

        Assert.assertEquals(userCreatedMessage, "Unable to create user. Id, first name, and last name are required");
    }

    @Test
    public void createUserBlankLastNameReturnsNoUpdate(){
        UserDTO userToUpdate = new UserDTO(3, "Created User ", " ", "Created Address");

        String userCreatedMessage = crudExampleService.createUser(userToUpdate);

        Assert.assertEquals(userCreatedMessage, "Unable to create user. Id, first name, and last name are required");
    }

    @Test
    public void deleteUserRemovesFromUserList(){
        List<UserEntity> users = crudExampleService.getAllUsers();

        crudExampleService.deleteUser(3);

        Assert.assertEquals(users.size(), 4);
    }

    @Test
    public void deleteUserNotFoundReturnsErrorMessage(){
        String userNotDeletedMessage = crudExampleService.deleteUser(5);

        Assert.assertEquals(userNotDeletedMessage, "User not found to delete");
    }

    @Test
    public void updateUserBlankLastNameReturnsNoUpdate(){
        UserDTO userToUpdate = new UserDTO(3, "Updated User ", " ", "Updated Address");

        String userUpdatedMessage = crudExampleService.updateUser(userToUpdate);

        Assert.assertEquals(userUpdatedMessage, "Unable to update user. Id, first name, and last name are required");
    }

    @Test
    public void updateUserHasDtoValues(){
        UserDTO userToUpdate = new UserDTO(3, "Updated First Name", "Updated Last Name", "Updated Address");

        crudExampleService.updateUser(userToUpdate);

        Optional<UserEntity> updatedUser = crudExampleService.getUserById(userToUpdate.getId());

        Assert.assertEquals(userToUpdate.getFirstName(), updatedUser.get().getFirstName());
        Assert.assertEquals(userToUpdate.getLastName(), updatedUser.get().getLastName());
        Assert.assertEquals(userToUpdate.getAddress(), updatedUser.get().getAddress());
    }

    @Test
    public void createUserVerify(){
        UserDTO userToCreate = new UserDTO(5, "Created First Name", "Created Last Name", "Created Address");

        crudExampleService.createUser(userToCreate);

        Optional<UserEntity> createdUser = crudExampleService.getUserById(userToCreate.getId());

        System.out.println("Users after all unit tests: " + crudExampleService.getAllUsers());

        List<UserEntity> users = crudExampleService.getAllUsers();

        Assert.assertEquals(userToCreate.getFirstName(), createdUser.get().getFirstName());
        Assert.assertEquals(userToCreate.getLastName(), createdUser.get().getLastName());
        Assert.assertEquals(userToCreate.getAddress(), createdUser.get().getAddress());
    }

}
