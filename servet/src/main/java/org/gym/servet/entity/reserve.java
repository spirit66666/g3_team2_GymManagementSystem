package org.gym.servet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@TableName("reservations")
public class reserve {


    @TableId(value = "reserveID",type = IdType.AUTO)
    private int reserveID;

    @TableField(value = "userID")
    private int userID;
    @TableField(value = "reserveDate")
    private String reserveDate;
    @TableField(value = "reserveTime")
    private String reserveTime;
    @TableField(value = "reserveNum")
    private int reserveNum;
    @TableField(value = "reserveStatus")
    private int reserveStatus;

    @TableField(value = "reserveNote")
    private String reserveNote;

}
