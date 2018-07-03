package com.zhao.verify;

import com.zhao.verify.util.LogUtil;
import com.zhao.verify.util.VerifyUtil;
import com.zhao.verify.validator.BaseValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-03 16:22
 * @描述
 */
public class VerifyProcessor {
    /**
    * @描述  对某个bean进行参数校验
    * @参数 [bean, extValidatorMap]
    * @返回值 com.zhao.verify.VerifyResult
    */
    public  static <T> VerifyResult process(T bean,Map<Class<? extends  Annotation>,BaseValidator> extValidatorMap){
        VerifyResult result = new VerifyResult();
        //获取框架和扩展的映射-{Annotation,BaseValidator}
        Map<Class<? extends Annotation>,BaseValidator> validatorMap =  VerifyUtil.getValidatorMapping(extValidatorMap);
        //bean校验前，判断是否需要校验
        Map<Field, Set<Annotation>> verifyAnnotation = VerifyBefore.getVerifyAnnotation(bean.getClass(),null==extValidatorMap?null:extValidatorMap.keySet());
        if(verifyAnnotation.isEmpty()){
            result.setMessage("当前对象不需要校验");
        }else{
            //开始校验
            for(Map.Entry<Field, Set<Annotation>>  entry:verifyAnnotation.entrySet()){
                Field field =  entry.getKey();
                Set<Annotation> values = entry.getValue();
                for(Annotation validateAnno:values){
                    //获取对应的注解校验器
                    BaseValidator validator = validatorMap.get(validateAnno.annotationType());
                    //校验
                    try {
                        BaseResult fieldVerifyResult = validator.valid(validateAnno,field.get((Object)bean));
                        if(fieldVerifyResult.isHasError()){
                            //参数校验不通过,把校验结果存到变量result中,返回给调用者
                            result.setHasError(true);
                            if(null==result.getErrorMap().get(field.getName())){
                                result.getErrorMap().put(field.getName(), new ArrayList<>());
                            }
                            result.getErrorMap().get(field.getName()).add(fieldVerifyResult.getMessage());
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        LogUtil.error("JVM异常:",e);
                    }
                }
            }
        }
        return result;
    }


    /**
    * @描述  对某个bean进行参数校验
    * @参数 [bean]
    * @返回值 com.zhao.verify.VerifyResult
    */
    public  static <T> VerifyResult process(T bean){
         return process(bean,null);
    }

}