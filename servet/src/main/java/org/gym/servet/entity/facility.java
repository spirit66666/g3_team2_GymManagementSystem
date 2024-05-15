package org.gym.servet.entity;


import com.baomidou.mybatisplus.annotation.IdType;
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

    @TableId(value = "facility_id", type = IdType.AUTO)
    private int facility_id;
    private String facility_name;
    private String facility_address;
    private String facility_phone;
    private String facility_email;
    private String facility_description;

}
