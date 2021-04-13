package com.graduationproject.studymanager.component;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class DateFormatterConvert implements Converter<Date,String> {

    @Override
    public String convert(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formatDate = sdf.format(date);//把时间转换成相应的时间字符串
        log.info("user_info_date={}",formatDate);
        return formatDate;
    }
}
