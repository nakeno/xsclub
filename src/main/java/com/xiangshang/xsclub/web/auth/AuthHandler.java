package com.xiangshang.xsclub.web.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AuthHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthHandler.class);

    public static final String SECURITY_AUTH_COOKIE_KEY = "identity_token";

    private String cookieName = SECURITY_AUTH_COOKIE_KEY;
    private String cookieDomain;
    private int tokenValiditySeconds = -1;
    private Boolean useSecureCookie = null;
    private Boolean httpOnly = true;

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getCookieDomain() {
        return cookieDomain;
    }

    public void setCookieDomain(String cookieDomain) {
        this.cookieDomain = cookieDomain;
    }

    public int getTokenValiditySeconds() {
        return tokenValiditySeconds;
    }

    public void setTokenValiditySeconds(int tokenValiditySeconds) {
        this.tokenValiditySeconds = tokenValiditySeconds;
    }

    public Boolean getUseSecureCookie() {
        return useSecureCookie;
    }

    public void setUseSecureCookie(Boolean useSecureCookie) {
        this.useSecureCookie = useSecureCookie;
    }

    public Boolean getHttpOnly() {
        return httpOnly;
    }

    public void setHttpOnly(Boolean httpOnly) {
        this.httpOnly = httpOnly;
    }

    public boolean autoLogin(HttpServletRequest request, HttpServletResponse response) {
        String authCookie = extractAuthCookie(request);

        if (authCookie == null) {
            return false;
        }

        LOGGER.debug("Remember-me cookie detected");

        if (authCookie.length() == 0) {
            LOGGER.debug("Cookie was empty");
            cancelCookie(request, response);
            return false;
        }

        return processAuthCookie(authCookie);
    }

    public String loginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String cookieValue = resolveAuthCookieValue(authentication);
        setCookie(cookieValue, tokenValiditySeconds, request, response);
        return cookieValue;
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        cancelCookie(request, response);
    }

    protected abstract String resolveAuthCookieValue(Authentication authentication);

    protected abstract boolean processAuthCookie(String authCookie);

    private String extractAuthCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if ((cookies == null) || (cookies.length == 0)) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }

    private void setCookie(String cookieValue, int maxAge, HttpServletRequest request,
                           HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, cookieValue);

        if (maxAge >= 0) {
            cookie.setMaxAge(maxAge);
        }

        cookie.setPath(getCookiePath(request));
        if (cookieDomain != null) {
            cookie.setDomain(cookieDomain);
        }
        if (maxAge < 1) {
            cookie.setVersion(1);
        }

        if (useSecureCookie == null) {
            cookie.setSecure(request.isSecure());
        } else {
            cookie.setSecure(useSecureCookie);
        }

        if (httpOnly == null) {
            cookie.setSecure(true);
        } else {
            cookie.setHttpOnly(httpOnly);
        }

        response.addCookie(cookie);
    }

    private void cancelCookie(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Cancelling cookie");

        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath(getCookiePath(request));
        if (cookieDomain != null) {
            cookie.setDomain(cookieDomain);
        }
        response.addCookie(cookie);
    }

    private String getCookiePath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return contextPath.length() > 0 ? contextPath : "/";
    }
}
