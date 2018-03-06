package com.xiangshang.xsclub.web.auth;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.SignerVerifier;

public class JwtAuthHandler extends AuthHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthHandler.class);

    private SignerVerifier signerVerifier;

    public JwtAuthHandler(String key) {
        this.signerVerifier = new MacSigner(key);
    }

    @Override
    protected String resolveAuthCookieValue(Authentication authentication) {
        return JwtHelper.encode(JSON.toJSONString(authentication), signerVerifier).getEncoded();
    }

    @Override
    protected boolean processAuthCookie(String authCookie) {
        Authentication authentication = decodeAndVerify(authCookie);
        if (authentication == null) {
            return false;
        }

        SecurityContext securityContext = new SecurityContext(authentication.getId());
        SecurityContextHolder.setContext(securityContext);
        return true;
    }

    private Authentication decodeAndVerify(String jwtToken) {
        try {
            Jwt jwt = JwtHelper.decodeAndVerify(jwtToken, signerVerifier);
            return JSON.parseObject(jwt.getClaims(), Authentication.class);
        } catch (Exception e) {
            LOGGER.error("decode and verify jwt content error,jwtToken:{}", jwtToken, e);
        }

        return null;
    }
}
