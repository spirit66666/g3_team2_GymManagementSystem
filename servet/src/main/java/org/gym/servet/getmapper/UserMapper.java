package org.gym.servet.getmapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.gym.servet.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {


}