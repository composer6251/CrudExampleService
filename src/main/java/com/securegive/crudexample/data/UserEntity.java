package com.securegive.crudexample.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserEntity {

    private int id;
    private String firstName;
    private String lastName;
    private String address;
}
