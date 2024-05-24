package org.gym.servet.getmapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.gym.servet.entity.User;
import org.gym.servet.entity.facility;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
@Repository
public interface getuser extends BaseMapper<User> {


    @Select("SELECT * FROM user limit #{pageNumber},#{pageSize}")
    List<User> selectPage(Integer pageNumber, Integer pageSize);

    @Select("SELECT COUNT(*) FROM user")
    Integer count();


}
