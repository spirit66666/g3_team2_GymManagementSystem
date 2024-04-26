package org.gym.servet.controll;

import org.apache.ibatis.annotations.Mapper;
import org.gym.servet.Result.Result;
import org.gym.servet.entity.LoginDTO;

@Mapper
public interface LoginService {
    public Result login(LoginDTO loginDTO);
}
