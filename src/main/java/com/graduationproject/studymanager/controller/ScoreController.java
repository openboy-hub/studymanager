package com.graduationproject.studymanager.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.graduationproject.studymanager.bean.Score;
import com.graduationproject.studymanager.bean.User;
import com.graduationproject.studymanager.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequestMapping("/score")
@Controller
public class ScoreController {
    @Autowired
    ScoreService scoreService;

    @GetMapping("/scoreManager")
    public String scoreManager(HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("loginUser");
        List<Score> score_list = scoreService.selectScoresInfo(user.getId());
        log.info(score_list.toString());
        model.addAttribute("score_list",score_list);
        return "/score/Score";
    }

    @PostMapping("/setNewScore")
    @ResponseBody
    public int setNewScore(@RequestParam("upload_date") String upload_date,
                            HttpSession httpSession){
        log.info(upload_date);
        User user = (User) httpSession.getAttribute("loginUser");
        scoreService.setNewScore(user.getId(), upload_date);
        return scoreService.getScoreId(user.getId());
    }
    @PostMapping("/deleteScoreInfo")
    @ResponseBody
    public void deleteScoreInfo(@RequestParam("score_id") Integer score_id){
        log.info(score_id.toString());
        scoreService.deleteScoreInfo(score_id);
    }

    @PostMapping("/deleteScoreImage")
    @ResponseBody
    public void deleteScoreImage(@RequestParam("image_name")String image_name,
                                 @RequestParam("score_id") Integer score_id){
        scoreService.deleteScoreImage(score_id,image_name);
        String filePath = "D:\\intellj2017\\studymanager\\src\\main\\resources\\static\\images\\score\\image\\"+image_name;
        File delFile = new File(filePath);
        delFile.deleteOnExit();
    }

    @PostMapping("/updateScore")
    @ResponseBody
    public void updateScore(@RequestParam("score") String score){
        JSONObject userJson = JSONObject.parseObject(score);
        Score score1 = JSON.toJavaObject(userJson,Score.class);
        scoreService.updateScore(score1);
    }

    @ResponseBody
    @PostMapping("/uploadScoreImage")
    public void uploadScoreImage(@RequestParam("file") MultipartFile file,
                                 @RequestParam("score_id") Integer score_id) throws IOException {
        log.info(file.getOriginalFilename()+score_id);
        if(!file.getOriginalFilename().equals("") && score_id!=0){
            String image = file.getOriginalFilename();
            String filePath = "D:\\intellj2017\\studymanager\\src\\main\\resources\\static\\images\\score\\image\\"+file.getOriginalFilename();
            scoreService.uploadScoreImage(image,score_id);
            file.transferTo(new File(filePath));
        }
    }
}
