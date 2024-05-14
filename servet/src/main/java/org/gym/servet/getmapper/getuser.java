package org.gym.servet.getmapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.gym.servet.entity.User;

import java.util.List;

@Mapper
public interface getuser{
    @Select("SELECT * FROM gym_user")
      List<User> find();



    @Select("SELECT * FROM gym_user limit #{pageNumber},#{pageSize}")
    List<User> selectPage(Integer pageNumber, Integer pageSize);

    @Select("SELECT COUNT(*) FROM gym_user")
    Integer count();

    @Insert("INSERT INTO gym_user(id, username, password) VALUES(#{id}, #{username}, #{password})")
    void insertUser(User user);

    @Update("UPDATE gym_user SET username = #{username}, password = #{password} WHERE id = #{id}")
    int updateUser(User user);
}
