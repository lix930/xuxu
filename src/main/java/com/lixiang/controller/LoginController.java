package com.lixiang.controller;

import com.lixiang.entity.UserEntity;
import com.lixiang.repository.UserRepository;
import com.lixiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2016/11/17.
 */
@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    UserService userService;

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public String Check_User(@RequestParam("email") String email, @RequestParam("password") String password) {
        UserEntity user = userRepository.findByUsername(email);

        if(user.getUsername() != null && user.getUsername().equals(email) && user.getPassword().equals(password))
            return "redirect:admin/blogs";
        else
            return "error";

    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String toSignUp() {
        return "signup";
    }

    @RequestMapping(value = "/services/users/check-user", method = RequestMethod.POST)
    public @ResponseBody UserEntity CheckUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        UserEntity user = userRepository.findByUsername(email);

        if(userService.exist(user.getUsername())) {
            if(user.getUsername().equals(email) && user.getPassword().equals(password))
                return user;
            else
                return user;
        }
        return user;
    }
}
