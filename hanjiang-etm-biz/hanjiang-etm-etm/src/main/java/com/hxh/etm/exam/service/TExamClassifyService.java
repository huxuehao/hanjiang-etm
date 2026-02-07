package com.hxh.etm.exam.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hxh.etm.common.dto.TExamClassifyDto;
import com.hxh.etm.common.entity.TExamClassify;

import java.util.List;

/**
 * 描述：
 *
 * @author huxuehao
 **/
public interface TExamClassifyService extends IService<TExamClassify> {

    List<TExamClassifyDto> examTree(QueryWrapper<TExamClassify> ew);
}
