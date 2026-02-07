package com.hxh.etm.auth.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxh.etm.auth.mapper.TMenuButtonMapper;
import com.hxh.etm.common.consts.DBConst;
import com.hxh.etm.common.dto.TMenuButtonDto;
import com.hxh.etm.common.entity.TMenuButton;
import com.hxh.etm.common.util.tree.TreeUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：菜单按钮
 *
 * @author huxuehao
 **/
@Service
public class TMenuButtonServiceImpl extends ServiceImpl<TMenuButtonMapper, TMenuButton> implements TMenuButtonService {
    @Override
    public List<TMenuButtonDto> tree() {
        List<TMenuButtonDto> list = baseMapper.listV2(DBConst.T_MENU, DBConst.T_MENU_BUTTON);
        return TreeUtil.convertTree(list);
    }
}
