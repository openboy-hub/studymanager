package com.graduationproject.studymanager.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.graduationproject.studymanager.bean.User;
import com.graduationproject.studymanager.service.EchartsService;
import com.graduationproject.studymanager.service.UserService;
import jdk.nashorn.internal.runtime.JSONFunctions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/echarts")
public class EchartController {
    @Autowired
    EchartsService echartsService;
    @PostMapping("/getPieData")
    @ResponseBody
    public String getPieData(HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        int user_id = user.getId();
        Map map =  echartsService.getPieData(user_id);
        return JSONObject.toJSONString(map);
    }
}
