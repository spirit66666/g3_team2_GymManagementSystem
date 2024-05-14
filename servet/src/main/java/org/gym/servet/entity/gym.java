package org.gym.servet.entity;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class gym {

    @GeneratedValue
    private int gym_id;
    private String gym_name;
    private String gym_address;
    private String gym_phone;
    private String gym_email;
    private String gym_website;
}
