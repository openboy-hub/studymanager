package com.graduationproject.studymanager.controller;

import com.graduationproject.studymanager.bean.User;
import com.graduationproject.studymanager.component.DateFormatterConvert;
import com.graduationproject.studymanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;


@Slf4j
@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping(value="/login")
    public String login(User user, HttpSession httpSession, Model model){
        if(!user.getUserName().equals("")&&!user.getPassWord().equals("")){
            User loginuser = userService.getUserByUserName(user.getUserName());
            if(loginuser!=null){
                if(!(loginuser.getPassWord().equals(""))&&loginuser.getPassWord().equals(user.getPassWord())){
                    httpSession.setAttribute("loginUser",loginuser);
                    return "/main";
                }
                model.addAttribute("login_msg","用户名或密码错误");
                return "/login";
            }
            model.addAttribute("login_msg","用户名不存在");
            return "/login";
        }
        model.addAttribute("login_msg","请输入用户名和密码");
        return "/login";
    }
    @GetMapping(value="/loginout")
    public String loginout(HttpSession httpSession){
        httpSession.removeAttribute("loginUser");
        return "/login";
    }

    @Autowired
    DateFormatterConvert dateFormatterConvert;

    @PostMapping(value="/register")
    public String register(@RequestParam("Email") String email,
                           @RequestParam("userName") String userName,
                           @RequestParam("passWord") String passWord,
                           Model model){
        if(userService.findOutExistEmail(email)!=0){
            model.addAttribute("register_msg","改邮箱已被注册");
            model.addAttribute("email",email);
            return "/login";
        }
        String create_date = dateFormatterConvert.convert(new Date());
        userService.setUser(userName,passWord,email,create_date);
        return "/login";
    }
    @PostMapping(value="/forget")
    public String forget(@RequestParam("Email")String email,Model model){
        User user = null;
        user = userService.getUserByUserName(email);
        if(user!=null){
            String userName=user.getUserName();
            String passWord=user.getPassWord();
            model.addAttribute("userName",userName);
            model.addAttribute("passWord",passWord);
            return "/findback";
        }
        model.addAttribute("forget_msg","用户信息不存在");
        return "/login";
    }
}
