package com.graduationproject.studymanager.mapper;

import com.graduationproject.studymanager.bean.Video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoMapper {
    public List<Video> getAllVideos(Integer user_id);
    public void uploadVideo(Video video);
    public void setVideoInfo(Integer video_id, String video_name, String category, String description);
    public void delVideos(List<Integer> delVideolist);
}
