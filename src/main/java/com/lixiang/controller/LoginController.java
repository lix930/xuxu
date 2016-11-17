package com.lixiang.controller;

import com.lixiang.model.UserEntity;
import com.lixiang.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2016/11/17.
 */
@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public String CheckUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        UserEntity user = userRepository.findByUsername(email);

        if(user.getUsername().equals(email) && user.getPassword().equals(password))
            return "admin/blogs";
        else
            return "error";
    }
}
