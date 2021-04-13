package com.graduationproject.studymanager.service;

import com.graduationproject.studymanager.bean.User;

public interface UserService {
    public User getUserByUserName(String username);
    public void setUser(String username, String passward, String email,String create_date);
    public User getUserByEmail(String email);
    public int findOutExistEmail(String email);
}
