package com.ele007.engine.entity;

import com.ele007.engine.annotation.Expressed;
import com.ele007.engine.annotation.ExpressedProps;
import com.ele007.engine.utils.RequstEnum;

import java.util.HashMap;
import java.util.List;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/3/11 16:54
 */
public class MapTarget {


    @Expressed
    private String expression;

    @ExpressedProps(struct = RequstEnum.MAP)
    private HashMap<String, Object> props;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public HashMap<String, Object> getProps() {
        return props;
    }

    public void setProps(HashMap<String, Object> props) {
        this.props = props;
    }
}
