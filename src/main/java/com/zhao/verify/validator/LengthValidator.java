package com.zhao.verify.validator;

import com.zhao.verify.BaseResult;
import com.zhao.verify.annotation.Length;

import java.lang.annotation.Annotation;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-03 15:54
 * @描述  字符串长度校验，不做非空校验，非空校验交给注解NotNull去处理
 */
public class LengthValidator extends  BaseValidator<String>{
    @Override
    public BaseResult valid(Annotation validate, String fieldValue){
        Length length = (Length)validate;
        if(null!=fieldValue){
            if(fieldValue.length()>length.max() || fieldValue.length()<length.min()){
                return  BaseResult.error(length.message());
            }
        }
        return BaseResult.ok();
    }
}