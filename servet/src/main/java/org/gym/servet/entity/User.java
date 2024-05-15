package org.gym.servet.entity;
import jakarta.persistence.GeneratedValue;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @GeneratedValue
    private Long id;
    private String username;
    private String password;


}

