package com.zhao.verify.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-03 15:36
 * @描述
 */
public class ClassUtil{
    /**
    * @描述  查找框架实现以及自定义的校验规则-没找到则返回空map
    * @参数 [beanClass]
    * @返回值 Set<Annotation>
    */
    public  static Map<Field,Set<Annotation>> getValidateAnnotationAndField(Class beanClass,Set<Class<? extends Annotation>> ext){
        //返回结果
        Map<Field,Set<Annotation>> result = new HashMap<>();

        //获取所有需要校验的注解
        Set<Class<? extends Annotation>> requireVerify = VerifyUtil.requireVerify(ext);
        //查找某个类的成员属性
        List<Field> fields = new ArrayList<>();
        injectMemberFieldsToList(beanClass, fields);
        //用来判断某个Field是否已经校验过了
        Set<String> fieldNameList = new HashSet<>();
        for(Field field:fields){
            if(fieldNameList.contains(field.getName())){
                //如果子类field存在校验注解，则用子类的，父类的就pass掉
                continue;
            }
             //如果当前类的filed上存在校验的注解，则用当前的，否则用父类继承的校验注解
            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
            Set<Annotation> verifyAnnoList = new HashSet<>();
            for(Annotation annotation:declaredAnnotations){
                //field存在校验注解
                if(requireVerify.contains(annotation.annotationType())){
                    fieldNameList.add(field.getName());
                    verifyAnnoList.add(annotation);
                    //禁用安全管理机制
                    field.setAccessible(true);
                }
            }
            if(!verifyAnnoList.isEmpty()){
                //field存在校验注解
                result.put(field,verifyAnnoList);
            }
        }
        return  result;
    }


    /**
    * @描述  查找某个类的成员属性,放到list中(包括父类的所有成员属性)
    * @参数 [beanClass, filedList]
    * @返回值 void
    */
    private static void injectMemberFieldsToList(Class beanClass,List<Field> filedList){
        //查找beanClass自身的成员属性
        Field[] selfFields = beanClass.getDeclaredFields();
        filedList.addAll(Arrays.asList(selfFields));
        beanClass = beanClass.getSuperclass();
        //递归查找父类的成员属性
        if(null!=beanClass){
            injectMemberFieldsToList(beanClass, filedList);
        }else{
            //递归完成，排除掉static属性
            for(int i=0;i<filedList.size();i++){
                boolean isStaticField = Modifier.isStatic(filedList.get(i).getModifiers());
                if(isStaticField) {
                    filedList.remove(i);
                    i--;
                }
            }
        }
    }
}