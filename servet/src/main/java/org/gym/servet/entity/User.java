package org.gym.servet.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
@TableName("user")
public class User {

    @TableId(value = "userID", type = IdType.AUTO)
    private int userID;

    @TableField(value = "userName")
    private String userName;
    @TableField(value = "passWord")
    private String passWord;
    @TableField(value = "mobilePhone")
    private String mobilePhone;
@TableField(value = "email")
    private String email;

}

