package com.hxh.etm.auth.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxh.etm.auth.mapper.TMenuApiMapper;
import com.hxh.etm.common.consts.DBConst;
import com.hxh.etm.common.dto.TMenuApiDto;
import com.hxh.etm.common.entity.TMenuApi;
import com.hxh.etm.common.util.tree.TreeUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：菜单接口
 *
 * @author huxuehao
 **/
@Service
public class TMenuApiServiceImpl extends ServiceImpl<TMenuApiMapper,TMenuApi> implements TMenuApiService {
    @Override
    public List<TMenuApiDto> tree() {
        List<TMenuApiDto> list = baseMapper.listV2(DBConst.T_MENU, DBConst.T_MENU_API);
        return TreeUtil.convertTree(list);
    }
}
