package com.xiangshang.xsclub.web.result;

import java.util.HashMap;
import java.util.Map;

public class MapResult extends Result<Map<String, Object>> {
    public MapResult(int code, String msg) {
        super(code, msg, new HashMap<String, Object>());
    }

    public MapResult put(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

    public MapResult putAll(Map<String, Object> m) {
        this.getData().putAll(m);
        return this;
    }
}
