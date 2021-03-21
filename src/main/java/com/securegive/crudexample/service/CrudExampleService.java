package com.securegive.crudexample.service;

import com.securegive.crudexample.data.MockUsersData;
import com.securegive.crudexample.data.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Slf4j
@Service
public class CrudExampleService {

    @Autowired
    MockUsersData mockUsersData;

    //Read
    public Optional<UserEntity> getUserById(int id){

        Optional<UserEntity> fetchedUser = getAllUsers().stream()
                .filter(user -> user.getId() == id)
                .findFirst();

        return fetchedUser;

    }

    public List<UserEntity> getAllUsers(){
        return mockUsersData.getAllUsers();
    }

    public String createUser(UserEntity userToCreate){


        if(nonNull(userToCreate.getId()) && nonNull(userToCreate.getFirstName()) && nonNull(userToCreate.getLastName())) {
            mockUsersData.getAllUsers().add(userToCreate);
            return "Successfully created user " + userToCreate.getFirstName() + " " + userToCreate.getLastName();
        }
        return "Unable to create user. Id, first name, and last name are required";
    }

    public String deleteUser(int id){
        Optional<UserEntity> fetchedUser = getUserById(id);
        if(fetchedUser.isPresent()){
            getAllUsers().remove(fetchedUser);
            return "User " + fetchedUser.get().getFirstName() + " " + fetchedUser.get().getLastName() + " has been deleted";
        }
        return "User not found to delete";
    }

    public Optional<UserEntity> updateUser(UserEntity updatedUser){

        Optional<UserEntity> userToUpdate = getUserById(updatedUser.getId());
        Optional<UserEntity> fetchedUser = getAllUsers().stream()
                .filter(user -> user.getId() == updatedUser.getId())
                .findFirst();
      //  mockUsersData.getAllUsers().replaceAll(UnaryOperator(userToUpdate));

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
