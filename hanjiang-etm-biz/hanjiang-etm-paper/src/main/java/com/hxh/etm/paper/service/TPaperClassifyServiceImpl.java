package com.hxh.etm.paper.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxh.etm.common.consts.DBConst;
import com.hxh.etm.common.dto.TPaperClassifyDto;
import com.hxh.etm.common.entity.TPaperClassify;
import com.hxh.etm.common.util.tree.TreeUtil;
import com.hxh.etm.paper.mapper.TPaperClassifyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：试卷分类
 *
 * @author huxuehao
 **/
@Service
public class TPaperClassifyServiceImpl extends ServiceImpl<TPaperClassifyMapper, TPaperClassify> implements TPaperClassifyService {
    @Override
    public List<TPaperClassifyDto> listV2(QueryWrapper<TPaperClassify> queryWrapper) {
        List<TPaperClassifyDto> tQuestionClassifies = this.baseMapper.listV2(DBConst.T_PAPER_CLASSIFY, queryWrapper);
        return TreeUtil.convertTree(tQuestionClassifies);
    }
}
