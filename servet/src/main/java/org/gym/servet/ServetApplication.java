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

    public static void main(String[] args) {
        SpringApplication.run(ServetApplication.class, args);
    }

}
