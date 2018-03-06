package com.xiangshang.xsclub.web.auth;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Authentication {
    private long id;
    private String nickName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public Authentication() {
    }

    public Authentication(long id, String nickName, Date createTime) {
        this.id = id;
        this.nickName = nickName;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
