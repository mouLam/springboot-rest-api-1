package com.moulam.springbootrestapi1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
