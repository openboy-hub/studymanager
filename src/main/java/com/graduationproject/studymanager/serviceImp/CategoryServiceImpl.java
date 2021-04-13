package com.graduationproject.studymanager.serviceImp;

import com.graduationproject.studymanager.mapper.CategoryMapper;
import com.graduationproject.studymanager.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public void addCategory(Integer user_id, String type, String category) {
        categoryMapper.addCategory(user_id, type, category);
    }

    @Override
    public List<String> getAll(Integer user_id, String type) {
        return categoryMapper.getAll(user_id, type);
    }

    @Override
    public void deleteCategory(Integer user_id, String type, List<String> cate_list) {
        categoryMapper.deleteCategory(user_id, type, cate_list);
    }

    @Override
    public void updateCategoryInfo(Integer id, String category) {
        categoryMapper.updateCategoryInfo(id, category);
    }
}
