package com.graduationproject.studymanager.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface EchartsMapper {
    public Map getPieData(int user_id);
}
