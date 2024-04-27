package org.gym.servet;

import org.gym.servet.entity.User;
import org.gym.servet.getmapper.UserMapper;
import org.gym.servet.getmapper.getuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@SpringBootApplication
@RestController
@CrossOrigin
public class ServetApplication {

    @Autowired
    getuser getuser;
    UserMapper userMapper;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userMapper.selectList(null);

    }



    @GetMapping("/us")
    public List<User> getUsers1() {

        List<User> users = getuser.find();
        return users ;

    }

    @PostMapping("/us")
    public User addUser(User user) {
        getuser.insert(user);
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServetApplication.class, args);
    }

}
