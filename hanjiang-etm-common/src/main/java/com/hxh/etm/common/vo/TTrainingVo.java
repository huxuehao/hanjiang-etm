package com.hxh.etm.common.vo;

import com.hxh.etm.common.entity.TTraining;
import com.hxh.etm.common.entity.TTrainingFile;
import com.hxh.etm.common.entity.TTrainingUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 描述：培训DTO
 *
 * @author huxuehao
 **/
@Getter
@Setter
public class TTrainingVo extends TTraining {
    List<Object> question;
    List<TTrainingFile> file;
    List<TTrainingUser> user;
}
