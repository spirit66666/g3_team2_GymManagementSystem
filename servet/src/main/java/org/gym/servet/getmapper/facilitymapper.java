package org.gym.servet.getmapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.gym.servet.entity.facility;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface facilitymapper extends BaseMapper<facility> {
}
