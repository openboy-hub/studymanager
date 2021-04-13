package com.graduationproject.studymanager.controller;

import com.graduationproject.studymanager.bean.NoteCategory;
import com.graduationproject.studymanager.bean.User;
import com.graduationproject.studymanager.service.CategoryService;
import com.graduationproject.studymanager.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    NoteService noteService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/noteManager")
    public String noteManager(HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("loginUser");
        List<NoteCategory> noteCategoryList = noteService.getAllNotes(user.getId(), "note");
        log.info(noteCategoryList.toString());
        model.addAttribute("noteCategoryList",noteCategoryList);
        return "/note/Note";
    }
}
