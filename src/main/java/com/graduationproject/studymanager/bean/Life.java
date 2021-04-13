package com.graduationproject.studymanager.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("life_circle")
public class Life {
    private Integer id;

    private User user;

    private String content;

    private Date make_date;

    private String mood;//枚举

    private Image image;

    private Video video;

}
