package org.amazing.framework.bean;

import org.amazing.framework.util.CastUtil;

import java.util.Map;

/**
 * 请求参数对象
 * Created by alex on 17-7-6.
 */
public class Param {
    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }


    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }
}
