package org.gym.servet.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gym.servet.entity.User;
import org.gym.servet.getmapper.getuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userservice extends ServiceImpl<getuser,User> {


    public boolean saveuser(User user){
        return save(user);
    }
}
