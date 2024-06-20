package org.gym.servet.controller;

import org.gym.servet.entity.facility;
import org.gym.servet.entity.gym;
import org.gym.servet.getmapper.facilitymapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
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

@Transactional
public class facilitycontroll {
    @Autowired
    private facilitymapper facilitymapper;


    @GetMapping("/getfacility")
    public List<facility> getfacility() {

        return facilitymapper.selectList(null);

    }
}
