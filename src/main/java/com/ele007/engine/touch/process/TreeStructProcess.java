package com.ele007.engine.touch.process;

import com.ele007.engine.utils.RequstEnum;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import java.util.HashMap;


/**
 * 处理数学计算树形结构数据
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/3/9 15:08
 */
public class TreeStructProcess<T> implements IProcessor<T> {

    @Override
    public boolean isSupport(T t, RequstEnum structType) {
        return RequstEnum.TREE == structType && t.getClass() != String.class;
    }

    public <T> T processCacula(String expression, HashMap<String, Object> map) {
//         防止出现精度丢失，将除法运算加入小数位，触发精度运算
        expression = expression.replace("/", "*1.00/");
        JexlContext jc = new MapContext();
        try {
            Expression e = new JexlEngine().createExpression(expression);
            Object result = e.evaluate(jc);
            return (T)result;
        } catch (Exception e) {
            throw new RuntimeException("计算错误 解析出计算公式：" + expression);
        }
    }

}
