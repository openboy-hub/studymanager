package com.graduationproject.studymanager.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("video")
public class Video {
    private Integer id;

    private User user;

    private String video_name;

    private String upload_date;

    private Integer collect;

    private String description;

    private String category;

    private String video_type;

    private long size;

}
