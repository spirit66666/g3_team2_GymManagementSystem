package org.gym.servet.Dao;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequest  {

    // Getters and setters
    private String username;
    private String password;
}
