package com.ele007.engine.touch.process;


import com.ele007.engine.utils.RequstEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/3/9 15:20
 */
public interface IProcessor<T> {

    boolean isSupport(T t, RequstEnum structType);

    <T> T processCacula(String expression, HashMap<String, Object> map);

}
