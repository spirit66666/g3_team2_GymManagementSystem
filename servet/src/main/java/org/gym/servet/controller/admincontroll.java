package org.gym.servet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.gym.servet.entity.User;
import org.gym.servet.entity.admin;
import org.gym.servet.getmapper.adminmapper;
import org.gym.servet.getmapper.getuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@SpringBootApplication
@RestController
//allow frontend to access backend
@CrossOrigin
public class admincontroll {

    @Autowired
    private adminmapper adminmapper;

    @GetMapping("/getadmin")
    public List<admin> getadmin() {

        return adminmapper.selectList(null);

    }

    @PostMapping("/postadmin")
    public int postadmin(@RequestBody admin admin) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        return adminmapper.insert(admin);
    }

}
