package com.ele007.engine.touch.process;

import com.alibaba.fastjson.JSON;
import com.ele007.engine.utils.RequstEnum;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import java.util.HashMap;

/**
 * 处理单一String表达式
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/3/9 18:42
 */
public class ExpreesionProcess<T> implements IProcessor<T> {

    @Override
    public boolean isSupport(T t, RequstEnum structType) {
        if (RequstEnum.STRING == structType && t.getClass() != String.class) {
            throw new RuntimeException("需传入String 类型数据或检查 @ExpressedProps 属性！，当前传入：" + JSON.toJSONString(t));
        }
        return RequstEnum.STRING == structType;
    }

    @Override
    public <T> T processCacula(String expression, HashMap<String, Object> map) {
        JexlContext jc = new MapContext();
        Expression e = new JexlEngine().createExpression(expression);
        Object result = e.evaluate(jc);
        return (T)result;
    }
}
