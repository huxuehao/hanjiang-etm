package com.hxh.etm.statistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hxh.etm.common.entity.TUserActive;
import com.hxh.etm.common.vo.UserActiveVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述：用户活跃
 *
 * @author huxuehao
 **/
@Mapper
public interface TUserActiveMapper extends BaseMapper<TUserActive> {
    List<UserActiveVo> selectUserActive(@Param("dbName") String dbName);
}
