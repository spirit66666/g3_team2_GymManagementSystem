package org.gym.servet;

import org.gym.servet.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.gym.servet.getmapper.mapper;

import java.util.List;

@ResponseBody
@SpringBootApplication
@RestController
public class ServetApplication {

    @Autowired
    private mapper mapper;

    public static void main(String[] args) {
        SpringApplication.run(ServetApplication.class, args);

    }


    @GetMapping("/")
    public List<User> hello() {
        User user = new User();

        return mapper.findAll();
    }
}
