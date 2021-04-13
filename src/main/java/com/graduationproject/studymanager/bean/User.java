package com.graduationproject.studymanager.bean;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("user")
public class User {

    /**
     * 所有属性都应该在数据库中
     */
    //@TableField(exist = false)  //当前属性表中不存在
    private String userName;
    //@TableField(exist = false)
    private String passWord;

    private String email;

    private Integer id;

    private String create_date;


}
