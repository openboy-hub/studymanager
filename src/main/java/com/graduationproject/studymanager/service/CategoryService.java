package com.graduationproject.studymanager.service;

import java.util.List;

public interface CategoryService {
    public void addCategory(Integer user_id, String type, String category);
    public List<String> getAll(Integer user_id, String type);
    public void deleteCategory(Integer user_id, String type, List<String> cate_list);
    public void updateCategoryInfo(Integer id, String category);
}
