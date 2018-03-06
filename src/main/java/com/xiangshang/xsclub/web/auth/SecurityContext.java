package com.xiangshang.xsclub.web.auth;

public class SecurityContext {
    private Long userId;

    public SecurityContext(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
