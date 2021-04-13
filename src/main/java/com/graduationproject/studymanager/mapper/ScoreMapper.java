package com.graduationproject.studymanager.mapper;

import com.graduationproject.studymanager.bean.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreMapper {
    public void setNewScore(Integer user_id, String upload_date);
    public int getScoreId(Integer user_id);
    public void updateScore(Score score);
    public void deleteScoreInfo(Integer id);
    public void deleteScoreImage(Integer score_id,String image_name);
    public List<Score> selectScoresInfo(Integer user_id);
    public void uploadScoreImage(String image, Integer score_id);
}
