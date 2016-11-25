package com.lixiang.controller;

import com.lixiang.model.Greeting;
import com.lixiang.entity.UserEntity;
import com.lixiang.model.User;
import com.lixiang.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/11/21.
 */

@RestController
public class TestController {

    @Autowired
    UserRepository userRepository;


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
    boolean TestRegister(@RequestParam(value = "email")String email, @RequestParam(value = "password")String password) {
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
    boolean Register_isExist(@RequestParam(value = "email")String email) {
        UserEntity userEntity = userRepository.findByUsername(email);
        return userEntity != null;
    }

}
