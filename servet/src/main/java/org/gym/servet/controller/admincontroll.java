package org.gym.servet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.gym.servet.Result.RestResult;
import org.gym.servet.Result.ResultGenerator;
import org.gym.servet.entity.User;
import org.gym.servet.entity.admin;
import org.gym.servet.getmapper.adminmapper;
import org.gym.servet.getmapper.getuser;
import org.gym.servet.service.AdminService;
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



    @Autowired
    private AdminService tbAdminService;

    @Autowired
    private ResultGenerator generator;

    @PostMapping( "/adminlogin")
    public RestResult adminlogin(String username, String password) {
        QueryWrapper<admin> wrapper = new QueryWrapper<>();
        wrapper.eq("adminName", username);
        admin one = tbAdminService.getOne(wrapper);
        if (null != username && null != password) {
            if (one.getAdminName().equalsIgnoreCase(username)) {
                if (one.getPassword().equalsIgnoreCase(password)) {
                    return generator.getSuccessResult();
                } else {
                    return generator.getFailResult("用户名或密码错误");
                }
            } else {
                return generator.getFailResult("用户名或密码错误");
            }
        } else {
            return generator.getFailResult("用户名或密码错误");
        }
    }

    @DeleteMapping("/deleteadmin/{id}")
    public RestResult deleteadmin(@PathVariable("id") Integer id) {
        QueryWrapper<admin> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        admin one = tbAdminService.getOne(wrapper);
        if (null != one) {
            tbAdminService.remove(wrapper);
            return generator.getSuccessResult();
        } else {
            return generator.getFailResult("删除失败");
        }
    }
}
