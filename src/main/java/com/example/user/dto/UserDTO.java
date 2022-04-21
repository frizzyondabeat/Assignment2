package com.example.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    String firstName;
    String lastName;
    String gender;
    String email;
    String username;
    String password;

}
