package com.graduationproject.studymanager.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("experience")
public class Experience {
    private Integer id;

    private User user;

    private Date exam_date;

    private String subject;

    private String exam_subject;

    private String description;

    private Integer score;

    private String _class;

}
