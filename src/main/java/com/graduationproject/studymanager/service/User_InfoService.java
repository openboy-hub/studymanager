package com.graduationproject.studymanager.service;

import com.graduationproject.studymanager.bean.User;
import com.graduationproject.studymanager.bean.User_Info;

import java.util.List;

public interface User_InfoService {
    public User_Info getUser_InfoByUser_Id(User user);
    public void setUser_Info(User_Info user_info);
    public void updateUser_Info(User_Info user_info);
    public void setUserImage(String image,Integer user_id);
}
