package com.hxh.etm.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxh.etm.common.dto.TMenuDto;
import com.hxh.etm.common.entity.TMenu;

import java.util.List;

/**
 * 描述：菜单
 *
 * @author huxuehao
 **/
public interface TMenuService extends IService<TMenu> {
    List<TMenuDto> tree(TMenu menu);
    boolean deleteAllById(List<Long> ids);

}
