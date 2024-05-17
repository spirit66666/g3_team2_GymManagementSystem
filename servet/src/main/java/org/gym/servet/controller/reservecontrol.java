package org.gym.servet.controller;

import org.gym.servet.entity.gym;
import org.gym.servet.entity.reserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.gym.servet.getmapper.reservemapper;

import java.util.List;

@ResponseBody
@SpringBootApplication
@RestController
//allow frontend to access backend
@CrossOrigin
public class reservecontrol {


    @Autowired

    private reservemapper reservemapper;


    @GetMapping("/getreserve")
    public List<reserve> getreservemapper() {

        return reservemapper.selectList(null);

    }

    @PostMapping("/addreserve")
    public void addreservemapper(@RequestBody reserve reserve) {
        reservemapper.insert(reserve);
    }


}
