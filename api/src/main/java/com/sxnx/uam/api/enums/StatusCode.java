package com.sxnx.uam.api.enums;

/**
* 公司：普元金融—F2E
* 文件名：StatusCode
* 作者：duanwenhong(段文红)
* 邮件：duanwh@primeton.com
* 时间：2020-2-10 9:26
* 描述：返回信息枚举类
*/
public enum StatusCode {

    Success(0,"成功"),
    Fail(-1,"失败"),
    InvalidParams(201,"非法的参数!"),

    UserNamePasswordNotBlank(50000,"用户账号或密码不能为空!"),
    AccessTokenNotBlank(50001,"accessToken必填，请在请求头header中塞入该字段"),

    TokenValidateExpireToken(60001,"Token已过期"),
    TokenValidateCheckFail(60002,"Token验证失败"),

    AccessTokenNotExist(70001,"Token不存在-请重新登录!"),
    AccessTokenInvalidate(70002,"无效的Token!"),

    AccessTokenNotExistRedis(80001,"Token不存在或已经过期-请重新登录!"),

    AccessSessionNotExist(90001,"用户没登录或登录Session已经过期-请重新登录!"),

    LoginFail(100000,"登录失败！"),
    CurrUserHasNotPermission(100001,"当前用户没有权限访问该资源或者操作！"),
    CurrUserNotLogin(100002,"当前用户没有登录，请先进行登录！"),

    ;

    private Integer code;
    private String msg;


    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}