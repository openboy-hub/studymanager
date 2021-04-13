package com.graduationproject.studymanager.controller;

import com.github.dadiyang.equator.Equator;
import com.github.dadiyang.equator.FieldInfo;
import com.github.dadiyang.equator.GetterBaseEquator;
import com.graduationproject.studymanager.bean.User;
import com.graduationproject.studymanager.bean.User_Info;
import com.graduationproject.studymanager.service.User_InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/individual")
public class IndividualController {
    @Autowired
    User_InfoService user_infoService;

    @GetMapping("/individual/{pattern}")
    public String individual(@PathVariable("pattern") String pattern, HttpSession session, Model model) {
        log.info(pattern);
        User user = (User) session.getAttribute("loginUser");
        User_Info user_info = user_infoService.getUser_InfoByUser_Id(user);
        if (user_info == null) return "/individual/setIndividual";
        log.info(user_info.toString());
        model.addAttribute("user_info", user_info);
        if(pattern.equals("show")) return "/individual/individual";
        else return "/individual/setIndividual";
    }
    @PostMapping("/postIndividual")
    public String postIndividual(User_Info user_info,HttpSession session) {
        User user = (User)session.getAttribute("loginUser");
        User_Info user_info1 = user_infoService.getUser_InfoByUser_Id(user);
        user_info.getUser().setId(user.getId());
        if (user_info1.getId() != 0) {
            user_info.setId(user_info1.getId());
            session.setAttribute("user_info",user_info);
            return "redirect:/individual/updateIndividual";
        }
        user_infoService.setUser_Info(user_info);
        return "redirect:/individual/individual";
    }

    @GetMapping("/updateIndividual")
    public String updateIndividual(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        User_Info user_info = (User_Info) session.getAttribute("user_info");
        User_Info user_info_old = user_infoService.getUser_InfoByUser_Id(user);
        Equator equator = new GetterBaseEquator();
        if(!equator.isEquals(user_info,user_info_old)){
            List<FieldInfo> diffInfo = equator.getDiffFields(user_info,user_info_old);
            log.info(diffInfo.toString());
            User_Info user_info_new = new User_Info();
            for(FieldInfo item : diffInfo){
                switch(item.getFieldName()){
                    case "address": user_info_new.setAddress((String)item.getFirstVal()); break;
                    case "user":user_info_new.setUser((User)item.getFirstVal()); break;
                    case "birthday":user_info_new.setBirthday((Date)item.getFirstVal()); break;
                    case "sign":user_info_new.setSign((String) item.getFirstVal());break;
                    case "hobby":user_info_new.setHobby((String) item.getFirstVal());break;
                    case "diploma":user_info_new.setDiploma((String) item.getFirstVal());break;
                    case "realname":user_info_new.setRealname((String) item.getFirstVal());break;
                    case "phone":user_info_new.setPhone((String) item.getFirstVal());break;
                    case "age":user_info_new.setAge((Integer) item.getFirstVal());break;
                    case "sex":user_info_new.setSex((String) item.getFirstVal());break;
                }
            }
            log.info(user_info_new.toString());
            user_infoService.updateUser_Info(user_info_new);
        }
        return "redirect:/individual/individual/show";
    }

    @PostMapping("uploadImage")
    public String uploadImage(@RequestParam("myFile")MultipartFile[] files,HttpSession httpSession) throws IOException {
        if(files.length>0) {
            User user = (User) httpSession.getAttribute("loginUser");
            for (MultipartFile file : files) {
                String image = file.getOriginalFilename();
                file.transferTo(new File("D:\\intellj2017\\studymanager\\src\\main\\resources\\static\\images\\userImage\\" + image));
                if(image!=null&&!image.equals(user.getUserName()+"_res.png")){
                    int user_id = user.getId();
                    user_infoService.setUserImage(image, user_id);
                }
            }
        }
        return "redirect:/individual/individual/show";
    }
}
