package com.zhao.verify.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-03 15:31
 * @描述   非空校验
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotNull{
    /**
    * @描述  错误提示消息
    * @参数 []
    * @返回值 java.lang.String
    */
    String message() default "";
}
