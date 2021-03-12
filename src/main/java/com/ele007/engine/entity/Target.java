package com.ele007.engine.entity;

import com.ele007.engine.annotation.Expressed;
import com.ele007.engine.annotation.ExpressedProps;
import com.ele007.engine.annotation.ExpressedValue;
import com.ele007.engine.utils.RequstEnum;

import java.util.List;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/3/10 11:01
 */
public class Target {

    @Expressed
    private String expression;

    @ExpressedProps(struct = RequstEnum.TREE)
    private Prop props;

    public static class Prop<T>{

        private String name;

        @ExpressedValue
        private T value;

        private Prop props;

        public Prop getProps() {
            return props;
        }

        public void setProps(Prop props) {
            this.props = props;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

    }


    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Prop getProps() {
        return props;
    }

    public void setProps(Prop props) {
        this.props = props;
    }
}
