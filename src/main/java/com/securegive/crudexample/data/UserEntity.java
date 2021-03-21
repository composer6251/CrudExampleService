package com.securegive.crudexample.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserEntity {

    private int id;
    private String firstName;
    private String lastName;
    private String address;
}
