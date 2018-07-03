package com.zhao.verify.util;

import com.zhao.verify.annotation.*;
import com.zhao.verify.validator.*;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-03 18:18
 * @描述
 */
public class VerifyUtil {
    private static  Map<Class<? extends Annotation>,BaseValidator> ANNO_VALIDATOR = new HashMap();
    /**
     * @描述  获取所有需要校验的注解
     * @参数 [ext]
     * @返回值 java.util.Set<java.lang.Class<? extends java.lang.annotation.Annotation>>
     */
    public static Set<Class<? extends Annotation>> requireVerify(Set<Class<? extends Annotation>> ext){
        Set<Class<? extends Annotation>> annotations = new HashSet<>();
        annotations.add(Length.class);
        annotations.add(NotNull.class);
        annotations.add(NumberMax.class);
        annotations.add(NumberMin.class);
        annotations.add(Regular.class);
        if(null != ext){
            annotations.addAll(ext);
        }
        return  annotations;
    }

    static {
        ANNO_VALIDATOR.put(Length.class,new LengthValidator());
        ANNO_VALIDATOR.put(NotNull.class,new NotNullValidator());
        ANNO_VALIDATOR.put(NumberMax.class,new NumberMaxValidator());
        ANNO_VALIDATOR.put(NumberMin.class,new NumberMinValidator());
        ANNO_VALIDATOR.put(Regular.class,new RegularValidator());
        //不可修改的map
        ANNO_VALIDATOR =  Collections.unmodifiableMap(ANNO_VALIDATOR);
    }


    /**
    * @描述  获取框架和扩展的映射-{Annotation,BaseValidator}
    * @参数 [extValidatorMap]
    * @返回值 java.util.Map<java.lang.Class<? extends java.lang.annotation.Annotation>,com.zhao.verify.validator.BaseValidator>
    */
    public static Map<Class<? extends Annotation>, BaseValidator> getValidatorMapping(Map<Class<? extends Annotation>, BaseValidator> extValidatorMap) {
        if(null==extValidatorMap){
            return ANNO_VALIDATOR;
        }
       for(Map.Entry<Class<? extends Annotation>,BaseValidator> entry:ANNO_VALIDATOR.entrySet()){
           if(!extValidatorMap.containsKey(entry.getKey())){
               //todo:如果扩展了框架内部的Validator，则用扩展的，没有扩展，则用框架默认的
               extValidatorMap.put(entry.getKey(),entry.getValue());
           }
       }
        return extValidatorMap;
    }
}