package org.gym.servet;

import org.gym.servet.entity.User;
import org.gym.servet.getmapper.UserMapper;
import org.gym.servet.getmapper.getuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
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
public class ServetApplication {

    @Autowired
    getuser getuser;



    @GetMapping("/page")
    public Map<String, Object>  findPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        pageNumber = (pageNumber - 1)*pageSize;
        Integer total = getuser.count();
        List<User> data = getuser.selectPage(pageNumber, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("total", total);

        return map;


    }

    @GetMapping("/us")
    public List<User> getUsers1() {

        List<User> users = getuser.find();
        return users ;

    }

    @PutMapping("/fetch")
    public List<User> getUsers12() {

        List<User> users = getuser.find();
        return users ;
    }

    @PostMapping("/post")
    public User addUser(User user) {
        getuser.insertUser(user);
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServetApplication.class, args);
    }

}
