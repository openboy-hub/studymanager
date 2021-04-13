package com.graduationproject.studymanager.serviceImp;

import com.graduationproject.studymanager.bean.Video;
import com.graduationproject.studymanager.mapper.VideoMapper;
import com.graduationproject.studymanager.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper videoMapper;

    @Override
    public void delVideos(List<Integer> delVideolist) {
        videoMapper.delVideos(delVideolist);
    }

    @Override
    public List<Video> getAllVideos(Integer user_id) {
        return videoMapper.getAllVideos(user_id);
    }

    @Override
    public void uploadVideo(Video video) {
        videoMapper.uploadVideo(video);
    }

    @Override
    public void setVideoInfo(Integer video_id, String video_name, String category, String description) {
        videoMapper.setVideoInfo(video_id,video_name, category,description);
    }
}
