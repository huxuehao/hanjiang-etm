package com.hxh.etm.auth.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxh.etm.auth.mapper.TRoleAuthMapper;
import com.hxh.etm.common.entity.TRoleAuth;
import org.springframework.stereotype.Service;

/**
 * 描述：角色权限
 *
 * @author huxuehao
 **/
@Service
public class TRoleAuthServiceImpl extends ServiceImpl<TRoleAuthMapper, TRoleAuth> implements TRoleAuthService {
}
