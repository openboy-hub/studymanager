package com.graduationproject.studymanager.serviceImp;

import com.graduationproject.studymanager.bean.Image;
import com.graduationproject.studymanager.mapper.ImageMapper;
import com.graduationproject.studymanager.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageMapper imageMapper;

    @Override
    public List<Image> selectAllImage(Integer user_id) {
        return imageMapper.selectAllImage(user_id);
    }

    @Override
    public void deleteImages(List<Integer> delImgList) {
        imageMapper.deleteImages(delImgList);
    }

    @Override
    public void insertImage(List<Image> images) {
        imageMapper.insertImage(images);
    }

    @Override
    public void setImageInfo(Integer image_id, String image_name, String category, String description) {
        imageMapper.setImageInfo(image_id, image_name,category,description);
    }
}
