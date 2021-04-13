package com.graduationproject.studymanager.serviceImp;

import com.graduationproject.studymanager.bean.User;
import com.graduationproject.studymanager.mapper.UserMapper;
import com.graduationproject.studymanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserMapper userMapper;

    public User getUserByUserName(String username){
        return userMapper.getUserByUserName(username);
    }
    public void setUser(String username, String passward, String email, String create_date){
        userMapper.setUser(username, passward, email, create_date);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public int findOutExistEmail(String email) {
        return userMapper.findOutExistEmail(email);
    }
}
