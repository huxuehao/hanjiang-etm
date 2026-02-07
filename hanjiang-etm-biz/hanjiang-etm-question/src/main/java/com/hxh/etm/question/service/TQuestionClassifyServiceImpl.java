package com.hxh.etm.question.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxh.etm.common.consts.DBConst;
import com.hxh.etm.common.dto.TQuestionClassifyDto;
import com.hxh.etm.common.entity.TQuestionClassify;
import com.hxh.etm.common.util.tree.TreeUtil;
import com.hxh.etm.question.mapper.TQuestionClassifyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：试题分类
 *
 * @author huxuehao
 **/
@Service
public class TQuestionClassifyServiceImpl extends ServiceImpl<TQuestionClassifyMapper, TQuestionClassify> implements TQuestionClassifyService {

    @Override
    public List<TQuestionClassifyDto> listV2(QueryWrapper<TQuestionClassify> queryWrapper) {
        List<TQuestionClassifyDto> tQuestionClassifies = this.baseMapper.listV2(DBConst.T_QUESTION_CLASSIFY, queryWrapper);
        return TreeUtil.convertTree(tQuestionClassifies);
    }
}
