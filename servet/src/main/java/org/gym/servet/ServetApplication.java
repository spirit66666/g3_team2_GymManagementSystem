package org.gym.servet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.*;


@ResponseBody
@SpringBootApplication
@RestController
//allow frontend to access backend,controll<mapper<service
@CrossOrigin
@MapperScan("org.gym.servet.getmapper")
@ServletComponentScan
public class ServetApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServetApplication.class, args);
    }

}
