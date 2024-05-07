package org.gym.servet.entity;
import com.baomidou.mybatisplus.annotation.TableName;
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

