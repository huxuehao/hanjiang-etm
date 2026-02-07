package com.hxh.etm.common.dto;

import com.hxh.etm.common.entity.TQuestionTemplate;
import lombok.Getter;
import lombok.Setter;


/**
 * 描述：试卷模版DTO
 *
 * @author huxuehao
 **/
@Getter
@Setter
public class TQuestionTemplateDto extends TQuestionTemplate {
    private String rowKey;
}
