package com.hxh.etm.paper.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxh.etm.common.entity.TPaperQuestion;
import com.hxh.etm.paper.mapper.TPaperQuestionMapper;
import org.springframework.stereotype.Service;

/**
 * 描述：试卷试题
 *
 * @author huxuehao
 **/
@Service
public class TPaperQuestionServiceImpl extends ServiceImpl<TPaperQuestionMapper, TPaperQuestion> implements TPaperQuestionService {
}
