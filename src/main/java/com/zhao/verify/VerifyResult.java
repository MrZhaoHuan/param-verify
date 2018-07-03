package com.zhao.verify;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-03 15:55
 * @描述   校验结果
 */
@Getter
@Setter
public class VerifyResult extends BaseResult{

    private Map<String,List<String>> errorMap = new HashMap<>();

}