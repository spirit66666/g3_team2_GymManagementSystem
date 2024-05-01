package org.gym.servet.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;


}

