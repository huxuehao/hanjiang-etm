package com.hxh.etm.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxh.etm.common.dto.TMenuButtonDto;
import com.hxh.etm.common.entity.TMenuButton;

import java.util.List;

/**
 * 描述：菜单按钮
 *
 * @author huxuehao
 **/
public interface TMenuButtonService extends IService<TMenuButton> {
    List<TMenuButtonDto> tree();
}
