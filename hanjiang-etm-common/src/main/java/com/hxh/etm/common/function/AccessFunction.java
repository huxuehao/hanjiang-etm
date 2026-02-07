package com.hxh.etm.common.function;

import com.hxh.etm.common.entity.TTask;

/**
 * 描述：自定义函数式接口，实现对数据接入的属性设置
 *
 * @author huxuehao
 **/
@FunctionalInterface
public interface AccessFunction {
    void setAttribute(TTask userShow);
}
