package com.graduationproject.studymanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mail")
public class EmailController {
    @GetMapping("/mail")
    public String mail(){
        return "/mail/mail";
    }
    @GetMapping("/mail_compose")
    public String mail_compose(){
        return "/mail/mail_compose";
    }
    @GetMapping("/mail_view")
    public String mail_view(){
        return "/mail/mail_view";
    }
}
