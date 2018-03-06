package com.xiangshang.xsclub.web.result;

public enum ResultCode {
    OK(1, "OK"),
    //Server Error
    SERVER_ERROR(-1, "系统繁忙，请稍候再试"),
    PARAM_ERROR(-2, "参数错误"),
    AUTH_ERROR(-3, "需要登录认证"),
    NULL_USER(10001, "用户不存在"),
    NULL_REDBAG(10101, "红包不存在"),
    REDBAG_SCOPE_ERROR(10102, "红包范围错误"),
    REDBAG_STATUS_ERROR(10103, "红包状态错误"),
    NULL_REDBAGSENDITEM(10201, "红包发放记录不存在"),
    REDBAGSENDITEM_STATUS_ERROR(10202, "红包发放记录状态错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
