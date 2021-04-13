package com.graduationproject.studymanager.serviceImp;

import com.graduationproject.studymanager.bean.Score;
import com.graduationproject.studymanager.mapper.ScoreMapper;
import com.graduationproject.studymanager.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    ScoreMapper scoreMapper;

    @Override
    public void setNewScore(Integer user_id, String upload_date) {
        scoreMapper.setNewScore(user_id,upload_date);
    }

    @Override
    public void updateScore(Score score) {
        scoreMapper.updateScore(score);
    }

    @Override
    public void deleteScoreInfo(Integer id) {
        scoreMapper.deleteScoreInfo(id);
    }

    @Override
    public void deleteScoreImage(Integer score_id, String image_name) {
        scoreMapper.deleteScoreImage(score_id, image_name);
    }

    @Override
    public List<Score> selectScoresInfo(Integer user_id) {
        return scoreMapper.selectScoresInfo(user_id);
    }

    @Override
    public void uploadScoreImage(String image, Integer score_id) {
        scoreMapper.uploadScoreImage(image,score_id);
    }

    @Override
    public int getScoreId(Integer user_id) {
        return scoreMapper.getScoreId(user_id);
    }
}
