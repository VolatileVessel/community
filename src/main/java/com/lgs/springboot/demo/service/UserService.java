package com.lgs.springboot.demo.service;

import com.lgs.springboot.demo.mapper.UserMapper;
import com.lgs.springboot.demo.model.User;
import com.lgs.springboot.demo.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(example);
       // User dbUser= userMapper.findByAccountId(user.getAccountId());
       if (users.size() ==0){
           //插入
           user.setGmtCreate(System.currentTimeMillis());
           user.setGmtModified(user.getGmtModified());
           userMapper.insert(user);
       }else {
           //更新
           User dbUser = users.get(0);
           User updateUser = new User();

           updateUser.setAvatarUrl(user.getAvatarUrl());
           updateUser.setToken(user.getToken());
           updateUser.setName(user.getName());
           updateUser.setGmtModified(user.getGmtModified());
           UserExample userExample = new UserExample();
           userExample.createCriteria().andIdEqualTo(dbUser.getId());
           userMapper.updateByExampleSelective(updateUser,userExample);
       }
    }
}
