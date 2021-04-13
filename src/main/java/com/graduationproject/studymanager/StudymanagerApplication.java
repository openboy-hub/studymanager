package com.graduationproject.studymanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = "com.graduationproject.studymanager")
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class StudymanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudymanagerApplication.class, args);
    }

}
