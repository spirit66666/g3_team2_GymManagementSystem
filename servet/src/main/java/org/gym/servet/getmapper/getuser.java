package org.gym.servet.getmapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.gym.servet.entity.User;

import java.util.List;

@Mapper
public interface getuser{
    @Select("SELECT * FROM gym_user")
     public List<User> find();

    @Insert("INSERT INTO gym_user(id, username, password) VALUES(#{id}, #{username}, #{password})")
    public void insert(User user);

}
