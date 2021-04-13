package com.graduationproject.studymanager.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
@TableName("score")
public class Score {
    private Integer id;

    private User user;

    private String subject;

    private Integer score;

    private String upload_date;

    private String description;

    private String type;

    private List<String> score_image;

}
