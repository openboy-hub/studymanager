package com.graduationproject.studymanager.service;

import com.graduationproject.studymanager.bean.Image;

import java.util.List;

public interface ImageService {
    public void insertImage(List<Image> images);
    public List<Image> selectAllImage(Integer user_id);
    public void setImageInfo(Integer image_id, String image_name, String category, String description);
    public void deleteImages(List<Integer> delImgList);
}
