package com.ele007.engine.touch;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/3/10 17:42
 */
public class HandleExpressionTemplate {

    private static ExpressionDispatch dispatch = new ExpressionDispatch();
    private static ObjectMapper objectMapper = new ObjectMapper();

//    public <T> T process(Target t, Class<T> resultClass){
//        return objectMapper.convertValue(dispatch.process(t), resultClass);
//    }
//
//    public <T> T process(String expression, Class<T> resultClass){
//        return objectMapper.convertValue(dispatch.process(expression), resultClass);
//    }
    public <T> T process(Object t, Class<T> resultClass){
        return objectMapper.convertValue(dispatch.process(t), resultClass);
    }

}
