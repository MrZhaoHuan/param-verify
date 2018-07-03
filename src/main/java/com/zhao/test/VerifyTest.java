package com.zhao.test;

import com.alibaba.fastjson.JSONObject;
import com.zhao.param.UserSon;
import com.zhao.verify.VerifyProcessor;
import com.zhao.verify.VerifyResult;

import java.util.List;
import java.util.Map;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-03 16:20
 * @描述  校验测试(校验规则可继承,可扩展校验注解)
 */
public class VerifyTest {
    public static void main(String[] args) {
        UserSon userSon =  new UserSon();
        VerifyResult verifyResult = VerifyProcessor.process(userSon);
        if(verifyResult.isHasError()){
            //校验不通过,打印错误信息
            Map<String, List<String>> errorMap = verifyResult.getErrorMap();
            for(String fieldName:errorMap.keySet()){
                System.out.println("属性:"+fieldName+"   校验结果:"+JSONObject.toJSONString(errorMap.get(fieldName)));
            }
        }
    }
}