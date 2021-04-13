package com.graduationproject.studymanager.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("note")
public class Note {
    private Integer id;

    private User user;

    private String upload_date;

    private String subject;

    private String category;

    private Integer collect;

    private String type;

    private String file_name;

}
