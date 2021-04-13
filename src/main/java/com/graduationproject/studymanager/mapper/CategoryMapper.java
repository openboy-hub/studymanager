package com.graduationproject.studymanager.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    public void addCategory(Integer user_id, String type, String category);
    public List<String> getAll(Integer user_id, String type);
    public void deleteCategory(Integer user_id, String type, List<String> cate_list);
    public void updateCategoryInfo(Integer id, String category);
}
