package com.lgs.springboot.demo.service;

import com.lgs.springboot.demo.mapper.UserMapper;
import com.lgs.springboot.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
       User dbUser= userMapper.findByAccountId(user.getAccountId());
       if (dbUser==null){
           //插入
           user.setGmtCreate(System.currentTimeMillis());
           user.setGmtModified(user.getGmtModified());
           userMapper.insert(user);
       }else {
           //更新
           dbUser.setAvatarUrl(user.getAvatarUrl());
           dbUser.setToken(user.getToken());
           dbUser.setName(user.getName());
           dbUser.setGmtModified(user.getGmtModified());
           userMapper.update(dbUser);
       }
    }
}
