package org.gym.servet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @GetMapping("/getreserve/{userID}")
    public List<reserve> getreservemapper(@PathVariable("userID") String userId) {
        QueryWrapper<reserve> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userID", userId);
        return reservemapper.selectList(queryWrapper);
    }


    @GetMapping("/getreserve")
    public List<reserve> getreservemapper() {

        return reservemapper.selectList(null);

    }

    @PostMapping("/addreserve")
    public void addreservemapper(@RequestBody reserve reserve) {
        reservemapper.insert(reserve);
    }



    @DeleteMapping("/deletereserve/{reserveID}")
    public int  deletereserve(@PathVariable("reserveID") String reserveID) {
        return reservemapper.deleteById(reserveID);
    }


}
