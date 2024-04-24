package org.gym.servet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class user {
    private String name;
    private String pwd;
    private String email;
    private String phone;
}
