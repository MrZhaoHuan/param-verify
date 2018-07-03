package com.zhao.verify;

import com.zhao.verify.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-03 15:41
 * @描述  验证某个javabean是否需要校验(是否存在校验的注解)
 */
public class VerifyBefore{
    public static Map<Field,Set<Annotation>> getVerifyAnnotation(Class<?> beanClass,Set<Class<? extends  Annotation>> ext){
        //查找框架实现以及自定义的校验规则
        Map<Field,Set<Annotation>> annotationAndField = ClassUtil.getValidateAnnotationAndField(beanClass,ext);
        return  annotationAndField;
    }


}