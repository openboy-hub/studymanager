package com.graduationproject.studymanager.mapper;

import com.graduationproject.studymanager.bean.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {
    public void insertImage(List<Image> images);
    public List<Image> selectAllImage(Integer user_id);
    public void setImageInfo(Integer image_id, String image_name, String category, String description);
    public void deleteImages(List<Integer> delImglist);
}
