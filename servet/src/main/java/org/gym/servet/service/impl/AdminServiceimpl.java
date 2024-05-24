package org.gym.servet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gym.servet.entity.admin;
import org.gym.servet.getmapper.adminmapper;
import org.gym.servet.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceimpl  extends ServiceImpl<adminmapper, admin> implements AdminService {

}
