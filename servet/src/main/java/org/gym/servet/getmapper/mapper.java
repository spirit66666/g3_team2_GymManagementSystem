package org.gym.servet.getmapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.gym.servet.entity.User;

import java.util.List;
@Mapper
public interface mapper {
    @Select("SELECT * FROM name")
    List<User> findAll();
}
