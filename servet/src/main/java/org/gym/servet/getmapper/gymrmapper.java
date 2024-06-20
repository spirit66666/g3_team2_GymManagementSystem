package org.gym.servet.getmapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.gym.servet.entity.User;
import org.gym.servet.entity.gym;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface gymrmapper extends BaseMapper<gym> {

    @Select("SELECT * FROM gym limit #{pageNumber},#{pageSize}")
    List<gym> selectPage(Integer pageNumber, Integer pageSize);

    @Select("SELECT COUNT(*) FROM gym")
    Integer count();
}
