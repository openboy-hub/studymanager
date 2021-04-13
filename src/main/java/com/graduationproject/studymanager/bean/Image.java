package com.graduationproject.studymanager.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Data
@TableName("image")
@Slf4j
public class Image {
    private Integer id;

    private User user;

    private String image_name;

    private String upload_date;

    private Integer collect;

    private String description;

    private String category;

    private long size;

    private String image_type;

}
