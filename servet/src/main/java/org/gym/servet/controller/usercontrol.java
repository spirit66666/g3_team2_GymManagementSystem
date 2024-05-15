package org.gym.servet.controller;

import org.gym.servet.entity.User;
import org.gym.servet.getmapper.getuser;
import org.gym.servet.service.userservice;
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

    @Autowired
    private userservice userservice;


    @GetMapping("/page")
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
    public int addUser(User user) {

        return getuser.insert(user);
    }



}