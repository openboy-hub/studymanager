package com.graduationproject.studymanager.controller;

import com.graduationproject.studymanager.bean.Image;
import com.graduationproject.studymanager.bean.User;
import com.graduationproject.studymanager.component.DateFormatterConvert;
import com.graduationproject.studymanager.service.CategoryService;
import com.graduationproject.studymanager.service.ImageService;
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
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    ImageService imageService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/imageManager")
    public String imageManager(HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("loginUser");
        List<Image> image_list = imageService.selectAllImage(user.getId());
        log.info(image_list.toString());
        List<String> category_list = categoryService.getAll(user.getId(),"image");
        log.info(image_list.toString());
        model.addAttribute("category_list",category_list);
        model.addAttribute("image_list",image_list);
        return "/image/Image";
    }

    @GetMapping("/Image_Editor")
    public String Image_Editor(){
        return "/image/Image_Editor";
    }

    @Autowired
    DateFormatterConvert dateFormatterConvert;

    @PostMapping("/uploadPhotos")
    public String uploadPhotos(@RequestParam("myFile") MultipartFile[] files, HttpSession httpSession) throws IOException {
        if(files.length>0){
            List<Image> img_list = new ArrayList<>();
            User user = (User) httpSession.getAttribute("loginUser");
            for(MultipartFile file : files){
                long imgSize = file.getSize();
                String imgName = file.getOriginalFilename();
                String imgUpDate = dateFormatterConvert.convert(new Date());
                String imgType = file.getContentType();
                log.info("你上传的文件的名称为：{};时间为：{};类型为：{};大小为：{}",imgName,
                        imgUpDate,imgType,imgSize);
                Image image = new Image();
                image.setSize(imgSize);
                image.setImage_name(imgName);
                image.setUpload_date(imgUpDate);
                image.setUser(user);
                image.setImage_type(imgType);
                img_list.add(image);
                file.transferTo(new File("D:\\intellj2017\\studymanager\\src\\main\\resources\\static\\images\\gallery\\"+imgName));
            }
            imageService.insertImage(img_list);
        }
        return "redirect:/image/imageManager";
    }
    @PostMapping("/setImageInfo")
    public String setImageInfo(@RequestParam("id") Integer image_id,
                               @RequestParam("image_name") String image_name,
                               @RequestParam("category") String category,
                               @RequestParam("description") String description){
        imageService.setImageInfo(image_id,image_name,category,description);
        return "redirect:/image/imageManager";
    }
    @PostMapping("/deleteImage")
    public String deleteImage(@RequestParam("delImg") List<Integer> delImgList,
                              @RequestParam("delImgName") List<String> delImgName){
        log.info(delImgList.toString());
        imageService.deleteImages(delImgList);
        for(String img : delImgName){
            String filePath = "D:\\intellj2017\\studymanager\\src\\main\\resources\\static\\images\\gallery\\"+img;
            File delFile = new File(filePath);
            delFile.deleteOnExit();
        }
        return "redirect:/image/imageManager";
    }

}
