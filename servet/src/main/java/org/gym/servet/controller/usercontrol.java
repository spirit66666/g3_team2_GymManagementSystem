package org.gym.servet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.gym.servet.Result.RestResult;
import org.gym.servet.Result.ResultGenerator;
import org.gym.servet.entity.User;
import org.gym.servet.entity.admin;
import org.gym.servet.getmapper.getuser;
import org.gym.servet.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ResponseBody
@SpringBootApplication
@RestController
//allow frontend to access backend
@CrossOrigin
public class usercontrol {

    @Autowired
     private getuser getuser;



    @GetMapping("/pageuser")
    public Map<String, Object> findPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        pageNumber = (pageNumber - 1)*pageSize;
        Integer total = getuser.count();
        List<User> data = getuser.selectPage(pageNumber, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("total", total);
        return map;

    }


    @GetMapping("/getusers")

    public List<User> getUsers() {

        return getuser.selectList(null);

    }

    @PostMapping("/postuser")
    public void addUser(@RequestBody User user) {

         getuser.insert(user);
    }


    @DeleteMapping("/deleteuser/{userID}")
    public int deleteUser(@PathVariable("userID") Integer userID) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        return getuser.delete(wrapper.eq("userID",userID));}


    @Autowired
    private AdminService tbAdminService;
    @Autowired
    private ResultGenerator generator;
    @PostMapping( "/userlogin")
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

}
