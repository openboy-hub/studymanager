package com.graduationproject.studymanager.controller;

import com.graduationproject.studymanager.bean.User;
import com.graduationproject.studymanager.bean.Video;
import com.graduationproject.studymanager.component.DateFormatterConvert;
import com.graduationproject.studymanager.component.TrimVideoFace;
import com.graduationproject.studymanager.service.CategoryService;
import com.graduationproject.studymanager.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/video")
public class VideoController {
    @Autowired
    VideoService videoService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/videoManager")
    public String videoManager(HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("loginUser");
        int user_id = user.getId();
        List<Video> video_list = videoService.getAllVideos(user_id);
        List<String> category_list = categoryService.getAll(user_id,"video");
        log.info("查询视频的数量为：{},信息为：{}",video_list.size(),video_list);
        model.addAttribute("video_list",video_list);
        model.addAttribute("category_list",category_list);
        return "/video/Video";
    }

    @PostMapping("/setVideoInfo")
    public String setVideoInfo(@RequestParam("id") Integer video_id,
                               @RequestParam("video_name") String video_name,
                               @RequestParam("_class") String category,
                               @RequestParam("description") String description){
        videoService.setVideoInfo(video_id,video_name,category,description);
        return "redirect:/video/videoManager";
    }
    @Autowired
    DateFormatterConvert dateFormatterConvert;

    @PostMapping("/uploadVideos")
    public String uploadVideos(@RequestParam("myVideos") MultipartFile multipartFile,
                               HttpSession httpSession) throws Exception {
        if(multipartFile!=null){
            Video video = new Video();
            User user = (User) httpSession.getAttribute("loginUser");
            String video_name = multipartFile.getOriginalFilename();
            String update_date = dateFormatterConvert.convert(new Date());
            long size = multipartFile.getSize();
            String video_type = multipartFile.getContentType();
            video.setUser(user);
            video.setSize(size);
            video.setVideo_name(video_name);
            video.setUpload_date(update_date);
            video.setVideo_type(video_type);
            log.info("-----------{}--------------",video.toString());
            videoService.uploadVideo(video);
            multipartFile.transferTo(new File("D:\\intellj2017\\studymanager\\src\\main\\resources\\static\\video\\"+video_name));
            TrimVideoFace.fetchFrame("D:\\intellj2017\\studymanager\\src\\main\\resources\\static\\video\\"+video_name,
                    "D:\\intellj2017\\studymanager\\src\\main\\resources\\static\\video\\face\\"+video_name+".png");
        }
        return "redirect:/video/videoManager";
    }

    @PostMapping("/deleteVideo")
    public String deleteVideo(@RequestParam("delVideo")List<Integer> delVideoList,
                              @RequestParam("delVideoName") List<String> delVideoName){
        log.info(delVideoList.toString());
        videoService.delVideos(delVideoList);
        for(String video : delVideoName){
            File delFile_face = new File("D:\\intellj2017\\studymanager\\src\\main\\resources\\static\\video\\face\\"+video+".png");
            File delFile = new File("D:\\intellj2017\\studymanager\\src\\main\\resources\\static\\video\\"+video);
            delFile_face.deleteOnExit();
            delFile.deleteOnExit();
        }
        return "redirect:/video/videoManager";
    }
}
