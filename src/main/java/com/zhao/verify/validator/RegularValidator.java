package com.zhao.verify.validator;

import com.zhao.verify.BaseResult;

import java.lang.annotation.Annotation;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-03 15:54
 * @描述
 */
public class RegularValidator extends  BaseValidator{

    @Override
    public BaseResult valid(Annotation validate, Object fieldValue) {
        return null;
    }
}