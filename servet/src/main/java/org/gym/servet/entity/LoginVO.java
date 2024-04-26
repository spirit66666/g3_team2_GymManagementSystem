package org.gym.servet.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class LoginVO implements Serializable {
    private Integer id;
    private String token;
    private User user;
    //省略getter、setter
}

