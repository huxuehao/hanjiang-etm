package com.hxh.etm.paper.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hxh.etm.common.dto.TPaperClassifyDto;
import com.hxh.etm.common.entity.TPaperClassify;

import java.util.List;

/**
 * 描述：试卷分类
 *
 * @author huxuehao
 **/
public interface TPaperClassifyService extends IService<TPaperClassify> {
    List<TPaperClassifyDto> listV2(QueryWrapper<TPaperClassify> ew);
}
