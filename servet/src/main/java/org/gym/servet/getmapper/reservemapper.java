package org.gym.servet.getmapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.gym.servet.entity.gym;
import org.gym.servet.entity.reserve;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface reservemapper extends BaseMapper<reserve> {

    @Select("SELECT * FROM reservations limit #{pageNumber},#{pageSize}")
    List<reserve> selectPage(Integer pageNumber, Integer pageSize);

    @Select("SELECT COUNT(*) FROM reservations")
    Integer count();
}
