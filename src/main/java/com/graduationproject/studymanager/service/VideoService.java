package com.graduationproject.studymanager.service;

import com.graduationproject.studymanager.bean.Video;

import java.util.List;

public interface VideoService {
    public List<Video> getAllVideos(Integer user_id);
    public void setVideoInfo(Integer video_id, String video_name, String category, String description);
    public void uploadVideo(Video video);
    public void delVideos(List<Integer> delVideolist);
}
