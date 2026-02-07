package com.hxh.etm.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxh.etm.common.dto.TOrganizationDto;
import com.hxh.etm.common.entity.TOrganization;

import java.util.List;

/**
 * 描述：组织机构
 *
 * @author huxuehao
 **/
public interface TOrgService extends IService<TOrganization> {

    List<TOrganizationDto> tree(TOrganization org);

    boolean deleteAllById(List<Long> ids);
}
