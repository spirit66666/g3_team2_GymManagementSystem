package org.gym.servet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.gym.servet.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface Userservice extends IService<User> {
}
