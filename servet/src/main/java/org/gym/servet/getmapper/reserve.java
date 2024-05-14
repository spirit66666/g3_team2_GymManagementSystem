package org.gym.servet.getmapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.gym.servet.entity.facility;

import java.util.List;

@Mapper
public interface reserve {
    @Select("SELECT * FROM reserve WHERE reserveID = #{id}")
    List<facility> getReserveById(int id);
}
