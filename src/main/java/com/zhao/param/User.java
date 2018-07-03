package com.zhao.param;

import com.zhao.verify.annotation.Length;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-03 15:27
 * @描述
 */
@Getter
@Setter
@ToString
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    private long id;
    @Length(min = 6,max = 50,message = "用户名长度要求6-50之间")
    private String username="zhao";
    private int age;
    private double money;
}
