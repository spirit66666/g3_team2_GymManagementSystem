package org.gym.servet.controll;

import org.gym.servet.Result.Result;
import org.gym.servet.entity.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@ResponseBody
@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping(value = "/api/login")
    @CrossOrigin       //后端跨域
    public Result login(@RequestBody LoginDTO loginDTO){
        return loginService.login(loginDTO);
    }
}



