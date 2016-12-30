package com.lixiang.controller;

import com.lixiang.model.Greeting;
import com.lixiang.entity.UserEntity;
import com.lixiang.model.User;
import com.lixiang.repository.UserRepository;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
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

    @RequestMapping(value = "/api/home", method = RequestMethod.GET)
    public String TestHome() {
        return "1.html";
    }


    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public @ResponseBody String TestLogin(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        UserEntity userEntity = userRepository.findByUsername(email);
        if(userEntity != null) {
            if(userEntity.getUsername().equals(email) && userEntity.getPassword().equals(password))
                return "OK";
        }
        return "error";
    }

    @RequestMapping(value = "/api/login/get", method = RequestMethod.GET)
    public @ResponseBody String TestLogin1(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        UserEntity userEntity = userRepository.findByUsername(email);
        if(userEntity != null) {
            if(userEntity.getUsername().equals(email) && userEntity.getPassword().equals(password))
                return "OK";
        }
        return "error";
    }
    // 兼容老版 api
    // 有时候 部署到tomcat中，需要带项目名才能访问。。。。
    @RequestMapping(value = "springMVC/api/login/get", method = RequestMethod.GET)
    public @ResponseBody String TestLogin2(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        UserEntity userEntity = userRepository.findByUsername(email);
        if(userEntity != null) {
            if(userEntity.getUsername().equals(email) && userEntity.getPassword().equals(password))
                return "OK";
        }
        return "error";
    }
    //注册账号
    @RequestMapping(value = "/api/register", method = RequestMethod.POST)
    public @ResponseBody String TestRegister(@RequestParam(value = "email")String email, @RequestParam(value = "password")String password) {
        UserEntity userEntity = userRepository.findByUsername(email);
        if(userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setUsername(email);
            userEntity.setPassword(password);

            userRepository.saveAndFlush(userEntity);
            return "OK";
        }
        return "error";
    }

    @RequestMapping(value = "/api/register/get", method = RequestMethod.GET)
    public @ResponseBody String TestRegister1(@RequestParam(value = "email")String email, @RequestParam(value = "password")String password) {
        UserEntity userEntity = userRepository.findByUsername(email);
        if(userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setUsername(email);
            userEntity.setPassword(password);

            userRepository.saveAndFlush(userEntity);
            return "OK";
        }
        return "error";
    }

    // 兼容老版 api
    // 有时候 部署到tomcat中，需要带项目名才能访问。。。。
    @RequestMapping(value = "springMVC/api/register/get", method = RequestMethod.GET)
    public @ResponseBody String TestRegister2(@RequestParam(value = "email")String email, @RequestParam(value = "password")String password) {
        UserEntity userEntity = userRepository.findByUsername(email);
        if(userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setUsername(email);
            userEntity.setPassword(password);

            userRepository.saveAndFlush(userEntity);
            return "OK";
        }
        return "error";
    }
    //判断用户名是否 存在
    @RequestMapping(value = "/api/register/isexist", method = RequestMethod.GET)
    public @ResponseBody boolean Register_isExist(@RequestParam(value = "email")String email) {
        UserEntity userEntity = userRepository.findByUsername(email);
        return userEntity != null;
    }



    //发送 短信验证码
    /**
     * @ code：  发送的验证码
     * @ phone： 验证码接收的手机号
     * */
    @RequestMapping(value = "/test/register/sendcode", method = RequestMethod.GET)
    public @ResponseBody String SendCode(@RequestParam(value = "code") String code, @RequestParam(value = "phone") String phoneNumber) {
        String url = "http://gw.api.taobao.com/router/rest";
        String appkey = "23554532";
        String secret = "6f2ebe4c3a5ca2adfb76405827ad0b94";
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("");
        req.setSmsType("normal");
        req.setSmsFreeSignName("智能嘘嘘扣");
        req.setSmsParamString( "{code:'" + code +"'}" );
        req.setRecNum( phoneNumber );
        req.setSmsTemplateCode( "SMS_32760090" );

        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
            System.out.println(rsp.getBody());
            return rsp.getBody();

        } catch (ApiException e) {
            e.printStackTrace();
            return "error";
        }


    }


}
