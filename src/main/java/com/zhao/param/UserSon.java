package com.zhao.param;

import com.zhao.verify.annotation.NotNull;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-03 16:26
 * @描述
 */
public class UserSon extends User{
    @NotNull(message = "年龄不能为空")
    public Integer age;

}