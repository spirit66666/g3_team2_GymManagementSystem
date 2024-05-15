package org.gym.servet.controller;


import org.gym.servet.entity.User;
import org.gym.servet.entity.gym;
import org.gym.servet.getmapper.gymrmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ResponseBody
@SpringBootApplication
@RestController
//allow frontend to access backend
@CrossOrigin
public class gymcontrol {
    @Autowired
    private gymrmapper gymmapper;



    @GetMapping("/getgym")
    public List<gym> getgym() {

        return gymmapper.selectList(null);

    }
}
