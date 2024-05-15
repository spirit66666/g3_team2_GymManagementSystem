package org.gym.servet.controller;

import org.gym.servet.getmapper.reserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@SpringBootApplication
@RestController
//allow frontend to access backend
@CrossOrigin
public class reservecontrol {



}
