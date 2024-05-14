package org.gym.servet.entity;


import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class facility {

    @GeneratedValue
    private int facility_id;
    private String facility_name;
    private String facility_address;
    private String facility_phone;
    private String facility_email;
    private String facility_description;
}
