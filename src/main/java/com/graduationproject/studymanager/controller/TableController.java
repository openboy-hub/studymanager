package com.graduationproject.studymanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/table")
public class TableController {
    @GetMapping("/editable_table")
    public String table(){
        return "/table/Table";
    }
}
