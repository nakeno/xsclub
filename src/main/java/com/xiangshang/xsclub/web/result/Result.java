package com.xiangshang.xsclub.web.result;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public static Result OK() {
        return new Result(ResultCode.OK.getCode(), ResultCode.OK.getMsg());
    }

    public static <T> Result OK(T data) {
        return new Result(ResultCode.OK.getCode(), ResultCode.OK.getMsg(), data);
    }

    public static MapResult OKMap() {
        return new MapResult(ResultCode.OK.getCode(), ResultCode.OK.getMsg());
    }
}
