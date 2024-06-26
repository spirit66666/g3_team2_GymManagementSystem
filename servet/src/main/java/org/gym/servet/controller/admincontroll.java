package org.gym.servet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.gym.servet.DTO.AdminLoginRequest;
import org.gym.servet.Result.RestResult;
import org.gym.servet.Result.ResultGenerator;
import org.gym.servet.entity.User;
import org.gym.servet.entity.admin;
import org.gym.servet.getmapper.adminmapper;
import org.gym.servet.service.AdminService;
import org.gym.servet.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@SpringBootApplication
@RestController
//allow frontend to access backend
@CrossOrigin

@Transactional
public class admincontroll {

    @Autowired
    private adminmapper adminmapper;

    public admincontroll(org.gym.servet.utils.JwtUtil jwtUtil1, org.gym.servet.utils.JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil1;
        JwtUtil = jwtUtil;
    }

    @GetMapping("/getadmin/{adminName}")
    public List<admin> getadmin( @PathVariable String adminName) {
QueryWrapper<admin> qw = new QueryWrapper<>();
qw.eq("adminName", adminName);

        return adminmapper.selectList(qw);

    }

    @PostMapping("/postadmin")
    public int postadmin(@RequestBody admin admin) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        return adminmapper.insert(admin);
    }



    @Autowired
    private AdminService tbAdminService;
    private final JwtUtil jwtUtil;
    @Autowired
    private ResultGenerator generator;
    private final JwtUtil JwtUtil;
    @PostMapping( "/adminlogin")
    public RestResult adminlogin(@RequestBody AdminLoginRequest request) {


        String username = request.getUsername();
        String password = request.getPassword();
        QueryWrapper<admin> wrapper = new QueryWrapper<>();
        wrapper.eq("adminName", username);
        admin one = tbAdminService.getOne(wrapper);
        System.out.println(username + " " + password);
        if (null != username && null != password) {
            if (one.getAdminName().equalsIgnoreCase(username)) {
                if (one.getPassWord().equalsIgnoreCase(password)) {
                    String Adtoken = JwtUtil.getAdminToken((String.valueOf(one.getAdminID())), one.getPassWord());
                    System.out.println("AdminID" + one.getAdminID());
                    System.out.println("Password: " + one.getPassWord());
                    System.out.println("Generated Adtoken: " + Adtoken);
                    return generator.getSuccessResult( Adtoken );
                } else {
                    return generator.getFailResult("用户名或密码错误1");
                }
            } else {
                return generator.getFailResult("用户名或密码错误2");
            }
        } else {
            return generator.getFailResult("用户名或密码错误3");
        }
    }

    @DeleteMapping("/deleteadmin/{adminID}")
    public RestResult deleteadmin(@PathVariable("adminID") Integer id) {
        QueryWrapper<admin> wrapper = new QueryWrapper<>();
        wrapper.eq("adminID", id);
        admin one = tbAdminService.getOne(wrapper);
        if (null != one) {
            tbAdminService.remove(wrapper);
            return generator.getSuccessResult();
        } else {
            return generator.getFailResult("删除失败");
        }
    }
}
