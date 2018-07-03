package com.zhao.verify;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-07-03 19:03
 * @描述  注解对应的Validator校验结果
 */

public class BaseResult {
    private boolean hasError = false;
    private String message;

    public static BaseResult error(){
        return new BaseResult().setHasError(true);
    }

    public static BaseResult error(String message){
        return new BaseResult().setHasError(true).setMessage(message);
    }

    public static BaseResult ok(){
        return new BaseResult();
    }

    public boolean isHasError() {
        return hasError;
    }

    public BaseResult setHasError(boolean hasError) {
        this.hasError = hasError;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BaseResult setMessage(String message) {
        this.message = message;
        return this;
    }
}
