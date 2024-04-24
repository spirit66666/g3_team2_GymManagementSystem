package org.gym.servet;

import org.apache.ibatis.annotations.Mapper;
import org.gym.servet.entity.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

import org.gym.servet.entity.user;
import org.gym.servet.getmapper.mapper;

import java.util.List;

@SpringBootApplication
@RestController
public class ServetApplication {

    @Autowired
    private mapper mapper;

    public static void main(String[] args) {
        SpringApplication.run(ServetApplication.class, args);

    }


    @GetMapping("/")
    public List<user> hello() {
        user user = new user();

        return mapper.findAll();
    }
}
