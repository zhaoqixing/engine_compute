package com.ele007.engine.touch.process;

import com.ele007.engine.utils.RequstEnum;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import java.util.HashMap;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/3/11 16:03
 */
public class MapStructProcess<T> implements IProcessor<T> {

    @Override
    public boolean isSupport(T t, RequstEnum structType) {
        return RequstEnum.MAP == structType;
    }

    @Override
    public <T> T processCacula(String expression, HashMap<String, Object> map) {
        JexlContext jc = new MapContext();
        map.forEach((k, v) -> jc.set(k, v));
        Expression e = new JexlEngine().createExpression(expression);
        Object result = e.evaluate(jc);
        return (T)result;
    }
}
