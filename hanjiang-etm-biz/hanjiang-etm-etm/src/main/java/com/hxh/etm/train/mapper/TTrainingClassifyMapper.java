package com.hxh.etm.train.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hxh.etm.common.dto.TTrainingClassifyDto;
import com.hxh.etm.common.entity.TTrainingClassify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述：试卷分类
 *
 * @author huxuehao
 **/
@Mapper
public interface TTrainingClassifyMapper extends BaseMapper<TTrainingClassify> {
    List<TTrainingClassifyDto> listV2(@Param("t_training_classify") String tableName, @Param("ew") QueryWrapper<TTrainingClassify> ew);
}
