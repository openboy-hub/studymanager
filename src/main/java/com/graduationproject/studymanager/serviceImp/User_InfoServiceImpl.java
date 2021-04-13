package com.graduationproject.studymanager.serviceImp;

import com.graduationproject.studymanager.bean.User;
import com.graduationproject.studymanager.bean.User_Info;
import com.graduationproject.studymanager.mapper.User_InfoMapper;
import com.graduationproject.studymanager.service.User_InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_InfoServiceImpl implements User_InfoService {
    @Autowired
    User_InfoMapper user_infoMapper;

    @Override
    public User_Info getUser_InfoByUser_Id(User user) {
        return user_infoMapper.getUser_InfoByUser_Id(user.getId());
    }

    @Override
    public void setUser_Info(User_Info user_info) {
        user_infoMapper.setUser_Info(user_info);
    }

    @Override
    public void updateUser_Info(User_Info user_info) {
        user_infoMapper.updateUser_Info(user_info);
    }

    @Override
    public void setUserImage(String image, Integer user_id) {
        user_infoMapper.setUserImage(image,user_id);
    }
}
