package com.graduationproject.studymanager.controller;

import com.graduationproject.studymanager.bean.User;
import com.graduationproject.studymanager.mapper.CategoryMapper;
import com.graduationproject.studymanager.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @PostMapping("/addCategory/{type}")
    public void addCategory(@RequestParam("category") String category,
                            @PathVariable("type") String type,
                            HttpSession httpSession){
        User user = (User) httpSession.getAttribute("loginUser");
        categoryService.addCategory(user.getId(),type,category);
    }

    @PostMapping("/delCategory/{type}")
    public void delCategory(@RequestParam("delCate") List<String> cate_list,
                            @PathVariable("type") String type,
                            HttpSession httpSession){
        log.info(type+cate_list);
        User user = (User) httpSession.getAttribute("loginUser");
        categoryService.deleteCategory(user.getId(),type,cate_list);
    }
}
