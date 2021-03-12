package com.ele007.engine.utils;

import com.ele007.engine.entity.Target;

import java.util.Arrays;
import java.util.Map;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/3/10 10:32
 */
public enum RequstEnum {
    NULL("", null),
    STRING("string", String.class),
    MAP("map", Map.class),
    TREE("tree", Target.class);

    private String struct;
    private Object exampleType;

    RequstEnum() {

    }

    RequstEnum(String struct, Object exampleType) {
        this.struct = struct;
        this.exampleType = exampleType;
    }

    public static RequstEnum parseClass(Class tClass){
        return Arrays.stream(RequstEnum.values())
                .filter(item -> item.exampleType == tClass)
                .findFirst()
                .orElse(RequstEnum.NULL);
    }

    public String getStruct() {
        return struct;
    }

    public void setStruct(String struct) {
        this.struct = struct;
    }

    public Object getExampleType() {
        return exampleType;
    }

    public void setExampleType(Object exampleType) {
        this.exampleType = exampleType;
    }
}
