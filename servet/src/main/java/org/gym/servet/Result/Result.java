package org.gym.servet.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gym.servet.entity.LoginVO;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class Result {
    //相应码
    private Integer code;
    //信息
    private String message;
    //返回数据
    private Object data;

    //省略getter、setter、构造方法
}