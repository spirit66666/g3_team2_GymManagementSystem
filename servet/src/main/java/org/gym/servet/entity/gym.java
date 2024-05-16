package org.gym.servet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("gym")
public class gym {

    @TableId(value = "gymID",type = IdType.AUTO)
    private int gymID;

    @TableField(value = "gymName")
    private String gymName;
    @TableField(value = "gymAddress")
    private String gymAddress;

}
