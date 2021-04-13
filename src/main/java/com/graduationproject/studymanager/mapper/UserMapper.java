package com.graduationproject.studymanager.mapper;

import com.graduationproject.studymanager.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public User getUserByUserName(String username);
    public void setUser(String username,String password,String email,String create_date);
    public User getUserByEmail(String email);
    public int findOutExistEmail(String email);
}
