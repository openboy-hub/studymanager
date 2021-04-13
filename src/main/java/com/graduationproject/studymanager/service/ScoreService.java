package com.graduationproject.studymanager.service;

import com.graduationproject.studymanager.bean.Score;

import java.util.List;

public interface ScoreService {
    public void setNewScore(Integer user_id, String upload_date);
    public void updateScore(Score score);
    public void deleteScoreInfo(Integer id);
    public void deleteScoreImage(Integer score_id,String image_name);
    public List<Score> selectScoresInfo(Integer user_id);
    public int getScoreId(Integer user_id);
    public void uploadScoreImage(String image, Integer score_id);
}
