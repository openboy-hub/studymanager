package com.graduationproject.studymanager.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("mission")
public class Mission {
    private Integer id;

    private User user;

    private String content;

    private Date make_date;

    private String complete;

    private String description;

    private String _class;
}
