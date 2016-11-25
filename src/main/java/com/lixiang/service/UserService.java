package com.lixiang.service;

import com.lixiang.entity.UserEntity;
import com.lixiang.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/11/21.
 */
@Service
public class UserService {

    @Autowired UserRepository userRepository;

    public UserEntity crete(UserEntity user) {
        this.userRepository.save(user);
        return user;
    }

    public boolean exist(String username) {
        return this.userRepository.findByUsername(username) != null;
    }

    public Iterable<UserEntity> findAll() {
        return this.userRepository.findAll();
    }

    public UserEntity findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
