package com.lixiang.controller;

import com.lixiang.model.Greeting;
import com.lixiang.entity.UserEntity;
import com.lixiang.model.User;
import com.lixiang.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2016/11/21.
 */

@Controller
public class TestController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/test/home", method = RequestMethod.GET)
    public String TestHome() {
        return "test";
    }


    @RequestMapping(value = "/test/login", method = RequestMethod.POST)
    String TestLogin(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        UserEntity userEntity = userRepository.findByUsername(email);
        if(userEntity != null) {
            if(userEntity.getUsername().equals(email) && userEntity.getPassword().equals(password))
                return "success";
        }
        return "error";
    }
    //注册账号
    @RequestMapping(value = "/test/register", method = RequestMethod.POST)
    public @ResponseBody boolean TestRegister(@RequestParam(value = "email")String email, @RequestParam(value = "password")String password) {
        UserEntity userEntity = userRepository.findByUsername(email);
        if(userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setUsername(email);
            userEntity.setPassword(password);

            userRepository.saveAndFlush(userEntity);
            return true;
        }
        return false;
    }
    //判断用户名是否 存在
    @RequestMapping(value = "/test/register/isexist", method = RequestMethod.GET)
    public @ResponseBody boolean Register_isExist(@RequestParam(value = "email")String email) {
        UserEntity userEntity = userRepository.findByUsername(email);
        return userEntity != null;
    }



}
