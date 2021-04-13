package com.graduationproject.studymanager.serviceImp;

import com.graduationproject.studymanager.mapper.EchartsMapper;
import com.graduationproject.studymanager.service.EchartsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Slf4j
@Service
public class EchartsServiceImpl implements EchartsService {
    @Autowired
    EchartsMapper echartsMapper;
    @Override
    public Map getPieData(int user_id) {
        log.info("-----------------echarts打印数据--------------------");
        return echartsMapper.getPieData(user_id);
    }
}
