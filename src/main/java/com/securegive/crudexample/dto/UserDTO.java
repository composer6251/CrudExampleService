package com.securegive.crudexample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class UserDTO {

    @NonNull
    private int id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private String address;
}
