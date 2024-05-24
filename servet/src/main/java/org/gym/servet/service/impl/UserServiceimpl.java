package org.gym.servet.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gym.servet.entity.User;
import org.gym.servet.getmapper.getuser;
import org.gym.servet.service.Userservice;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl  extends ServiceImpl<getuser, User> implements Userservice {
}
