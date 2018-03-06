package com.xiangshang.xsclub.web.result;

public class ResultException extends RuntimeException {
    private int code;
    private String msg;

    public ResultException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultException(int code, String msg, Throwable cause) {
        super(cause);
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
