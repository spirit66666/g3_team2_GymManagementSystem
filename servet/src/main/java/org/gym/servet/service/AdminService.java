package org.gym.servet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.gym.servet.entity.admin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AdminService extends IService<admin> {
}
