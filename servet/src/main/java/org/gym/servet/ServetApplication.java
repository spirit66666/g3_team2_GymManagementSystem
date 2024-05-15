package org.gym.servet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

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
