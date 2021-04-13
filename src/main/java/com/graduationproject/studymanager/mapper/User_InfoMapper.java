package com.graduationproject.studymanager.mapper;

import com.graduationproject.studymanager.bean.User_Info;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface User_InfoMapper {
    public User_Info getUser_InfoByUser_Id(Integer user_id);
    public void setUser_Info(User_Info user_info);
    public void updateUser_Info(User_Info user_info);
    public void setUserImage(String image,Integer user_id);
}
