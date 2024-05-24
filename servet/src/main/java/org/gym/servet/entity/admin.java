package org.gym.servet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("administrator")
public class admin {

    @TableId(value = "adminID", type = IdType.AUTO)
    private Long adminID;

    @TableField(value = "adminName")
    private String adminName;

    @TableField(value = "passWord")
    private String passWord;


    @TableField(value = "mobilePhone")
    private String mobilePhone;

    @TableField(value = "email")
    private String email;
}
