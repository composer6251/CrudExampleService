package com.securegive.crudexample.service;

import com.securegive.crudexample.data.MockUsersData;
import com.securegive.crudexample.data.UserEntity;
import com.securegive.crudexample.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    public Optional<UserEntity> getUserById(int id){

        Optional<UserEntity> fetchedUser = getAllUsers().stream()
                .filter(user -> user.getId() == id)
                .findFirst();

        return fetchedUser;
    }

    public List<UserEntity> getAllUsers(){
        return mockUsersData.getAllUsers();
    }

    public String createUser(UserDTO userToCreate){

        if(nonNull(userToCreate.getId()) && !StringUtils.isBlank(userToCreate.getFirstName()) && !StringUtils.isBlank(userToCreate.getLastName())) {
            mockUsersData.getAllUsers().add(mapUserEntityFromDTO(userToCreate));
            return "Successfully created user " + userToCreate.getFirstName() + " " + userToCreate.getLastName();
        }
        return "Unable to create user. Id, first name, and last name are required";
    }

    public String deleteUser(int id){

        Optional<UserEntity> fetchedUser = getUserById(id);
        if(fetchedUser.isPresent()){
            getAllUsers().remove(fetchedUser.get());
            return "User " + fetchedUser.get().getFirstName() + " " + fetchedUser.get().getLastName() + " has been deleted";
        }
        return "User not found to delete";
    }

    public String updateUser(UserDTO updatedUser){

        if(!getUserById(updatedUser.getId()).isPresent()){
            return "Unable to find user to update for id: " + updatedUser.getId();
        }
        if(StringUtils.isBlank(updatedUser.getFirstName()) || StringUtils.isBlank(updatedUser.getLastName())){
            return "Unable to update user. Id, first name, and last name are required";
        }

        mockUsersData.getAllUsers().forEach(user -> {
            if(user.getId() == updatedUser.getId()){
                user.setFirstName(updatedUser.getFirstName());
                user.setLastName(updatedUser.getLastName());
                user.setAddress(updatedUser.getAddress());
            }
        });
        return "Updated user " + updatedUser.getFirstName() + " " + updatedUser.getLastName();
    }

    private UserEntity mapUserEntityFromDTO(UserDTO userDTO){
        return UserEntity.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .address(userDTO.getAddress())
                .build();
    }
}
