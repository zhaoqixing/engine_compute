package com.ele007.engine.touch;


import com.ele007.engine.factory.CalculaProcessorFactory;
import com.ele007.engine.touch.process.IProcessor;
import com.ele007.engine.utils.RequstEnum;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/3/9 16:19
 */
public class ExpressionDispatch {

    /**
     * 处理公式
     * @param t 当前支持
     *          1：支持传入String 解析表达式
     *          2：支持传入com.ele007.engine.entity.Target 类型，数学公式计算
     *          3：支持传入Map 类型
     * @return
     */
    public <T> T process(Object t){
        CalculaProcessorFactory factory = CalculaProcessorFactory.getProcessorFactory();
        RequstEnum structType = RequstParser.parseExpressedPropsValue(t);
        IProcessor process = factory.createProcessor(t, structType);
        String expreesionTemplate = RequstParser.getExpreesionTemplate(t);
        //如果T 是String，直接作为表达式传入处理器解析String
        if (RequstEnum.STRING == structType) {
            return (T)process.processCacula(expreesionTemplate, new HashMap());
        }
        //如果传入ExpressedProps 数据为map， 将直接将expression和map传入处理器
        Object expreesionProps = RequstParser.getExpreesionProps(t);
        if (RequstEnum.MAP == structType) {
            if (!(expreesionProps instanceof Map)) {
                throw new RuntimeException("@ExpressedProps 数据非Map类型");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            HashMap map = objectMapper.convertValue(expreesionProps, HashMap.class);
            return (T)process.processCacula(expreesionTemplate, map);
        }
        //如果传入 Tree 结构对象，将对象里数据替换表达式内容后处理
        if (RequstEnum.TREE == structType) {
            String expression = RequstParser.reductionExpressionByList(expreesionTemplate, expreesionProps);
            return (T)process.processCacula(expression, new HashMap());
        }
        return null;
    }

}
