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
@TableName("facility")
public class facility {

    @TableId(value = "facilityID", type = IdType.AUTO)
    private int facilityID;
    @TableField(value = "facilityName")
    private String facilityName;
    @TableField(value = "facilityDescription")
    private String facilityDescription;
    @TableField(value ="facilityNum")
    private String facilityNum;
    @TableField(value ="remainNum")
    private String remainNum;

}
