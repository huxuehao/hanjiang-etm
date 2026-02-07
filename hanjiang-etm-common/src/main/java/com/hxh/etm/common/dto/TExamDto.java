package com.hxh.etm.common.dto;

import com.hxh.etm.common.entity.TExam;
import com.hxh.etm.common.entity.TExamCorrector;
import com.hxh.etm.common.entity.TExamRandomOption;
import com.hxh.etm.common.entity.TExamUser;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class TExamDto extends TExam {
    // 固定试题
    private LinkedList<Object> question;
    // 参考人员
    private List<TExamUser> user;
    // 批改人员
    private List<TExamCorrector> corrector;
    // 随机题目配置
    private List<TExamRandomOption> option;
}
