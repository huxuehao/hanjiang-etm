package com.hxh.etm.statistics.service;

import com.hxh.etm.common.consts.DBConst;
import com.hxh.etm.common.vo.ExamTrainInfo;
import com.hxh.etm.common.vo.ExamTrainInfoVo;
import com.hxh.etm.common.vo.QuestionLevel;
import com.hxh.etm.common.vo.QuestionLevelVo;
import com.hxh.etm.exam.mapper.TExamMapper;
import com.hxh.etm.question.factory.enums.LevelType;
import com.hxh.etm.question.mapper.TQuestionTemplateMapper;
import com.hxh.etm.statistics.mapper.TUserActiveMapper;
import com.hxh.etm.train.mapper.TTrainingMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 描述：统计分析
 *
 * @author huxuehao
 **/
@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final TQuestionTemplateMapper questionTemplateMapper;
    private final TExamMapper examMapper;
    private final TTrainingMapper trainingMapper;
    private final TUserActiveMapper userActiveMapper;

    public StatisticsServiceImpl(TQuestionTemplateMapper questionTemplateMapper, TExamMapper examMapper, TTrainingMapper trainingMapper, TUserActiveMapper userActiveMapper) {
        this.questionTemplateMapper = questionTemplateMapper;
        this.examMapper = examMapper;
        this.trainingMapper = trainingMapper;
        this.userActiveMapper = userActiveMapper;
    }

    @Override
    public Object userActive() {
        return userActiveMapper.selectUserActive(DBConst.T_USER_ACTIVE);
    }

    @Override
    public Object questionType() {
        return questionTemplateMapper.selectQuestionType(DBConst.T_QUESTION_TEMPLATE);
    }

    @Override
    public Object questionLevel() {
        QuestionLevelVo levelVo = new QuestionLevelVo();
        List<QuestionLevel> one = questionTemplateMapper.selectQuestionLevel(DBConst.T_QUESTION_TEMPLATE, LevelType.one.value);
        levelVo.setOne(one);
        List<QuestionLevel> two = questionTemplateMapper.selectQuestionLevel(DBConst.T_QUESTION_TEMPLATE, LevelType.two.value);
        levelVo.setTwo(two);
        List<QuestionLevel> three = questionTemplateMapper.selectQuestionLevel(DBConst.T_QUESTION_TEMPLATE, LevelType.three.value);
        levelVo.setThree(three);
        List<QuestionLevel> four = questionTemplateMapper.selectQuestionLevel(DBConst.T_QUESTION_TEMPLATE, LevelType.four.value);
        levelVo.setFour(four);
        List<QuestionLevel> five = questionTemplateMapper.selectQuestionLevel(DBConst.T_QUESTION_TEMPLATE, LevelType.five.value);
        levelVo.setFive(five);

        return levelVo;
    }

    @Override
    public Object examTrainStatus() {
        ExamTrainInfoVo infoVo = new ExamTrainInfoVo();
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        List<ExamTrainInfo> examInfos = examMapper.selectExamInfo(DBConst.T_EXAM, currentTime);
        infoVo.setExam(examInfos);
        List<ExamTrainInfo> trainInfos = trainingMapper.selectTrainInfo(DBConst.T_TRAIN, currentTime);
        infoVo.setTrain(trainInfos);

        return infoVo;
    }
}
