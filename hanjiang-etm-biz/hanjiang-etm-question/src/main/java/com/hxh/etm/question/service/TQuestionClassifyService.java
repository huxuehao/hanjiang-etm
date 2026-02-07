package com.hxh.etm.question.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hxh.etm.common.dto.TQuestionClassifyDto;
import com.hxh.etm.common.entity.TQuestionClassify;

import java.util.List;

/**
 * 描述：试题分类
 *
 * @author huxuehao
 **/
public interface TQuestionClassifyService extends IService<TQuestionClassify> {
    List<TQuestionClassifyDto> listV2(QueryWrapper<TQuestionClassify> ew);
}
