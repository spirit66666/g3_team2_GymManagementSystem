package org.gym.servet.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@TableName("gym_user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;


}

