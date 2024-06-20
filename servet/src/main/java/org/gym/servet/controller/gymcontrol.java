package org.gym.servet.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.gym.servet.entity.User;
import org.gym.servet.entity.gym;
import org.gym.servet.getmapper.gymrmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ResponseBody
@SpringBootApplication
@RestController
//allow frontend to access backend
@CrossOrigin

@Transactional
public class gymcontrol {
    @Autowired
    private gymrmapper gymmapper;


    @PostMapping("/updategym")
    public int updategym(@RequestBody gym gym) {
        UpdateWrapper<gym> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("gymID", gym.getGymID());
        updateWrapper.set("gymName", gym.getGymName());
        updateWrapper.set("gymAddress", gym.getGymAddress());
        return gymmapper.update(updateWrapper);
    }

    @PostMapping("/addgym")
    public void addgym(@RequestBody gym gym) {
        gymmapper.insert(gym);
    }

    @DeleteMapping("/deletegym/{gymID}")
    public int  deletegym(@PathVariable("gymID") String gymID) {
       return gymmapper.deleteById(gymID);
    }


    @GetMapping("/pagegym")
    public Map<String, Object> findPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        pageNumber = (pageNumber - 1)*pageSize;
        Integer total = gymmapper.count();
        List<gym> data = gymmapper.selectPage(pageNumber, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("total", total);
        return map;

    }

    @GetMapping("/getgym")
    public List<gym> getgym() {

        return gymmapper.selectList(null);

    }
}
