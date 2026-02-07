package com.hxh.etm.common.vo;

import com.hxh.etm.common.entity.TPaperTemplate;
import com.hxh.etm.common.entity.TQuestionTemplate;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 描述：试卷模版Vo
 *
 * @author huxuehao
 **/
@Getter
@Setter
public class TPaperTemplateVo extends TPaperTemplate {
    private List<TQuestionTemplate> question;
}
