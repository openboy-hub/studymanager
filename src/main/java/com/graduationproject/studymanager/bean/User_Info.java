package com.graduationproject.studymanager.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("user_info")
public class User_Info {

    private Integer id;

    private User user;

    private Integer age;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date birthday;

    private String sex;//枚举

    private String hobby;

    private String sign;

    private String diploma;//枚举

    private String realname;

    private String address;

    private String phone;

    private String image;
}
