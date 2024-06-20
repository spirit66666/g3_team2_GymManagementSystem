package org.gym.servet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.gym.servet.entity.User;
import org.gym.servet.entity.gym;
import org.gym.servet.entity.reserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.gym.servet.getmapper.reservemapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ResponseBody
@SpringBootApplication
@RestController
//allow frontend to access backend
@CrossOrigin
@Transactional

public class reservecontrol {


    @Autowired

    private reservemapper reservemapper;

    @GetMapping("/getreserve/{userID}")
    public List<reserve> getreservemapper(@PathVariable("userID") String userId) {
        QueryWrapper<reserve> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userID", userId);
        return reservemapper.selectList(queryWrapper);
    }


    @GetMapping("/getreserve") //管理员查看所有预约
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


    @GetMapping("/pageuserreserve")
    public Map<String, Object> findPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {

        
        pageNumber = (pageNumber - 1)*pageSize;
        Integer total = reservemapper.count();
        List<reserve> data = reservemapper.selectPage(pageNumber, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("total", total);
        return map;

    }

}
