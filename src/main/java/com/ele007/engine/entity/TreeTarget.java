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
public class TreeTarget {

    @Expressed
    private String expression;

    @ExpressedProps(struct = RequstEnum.TREE)
    private List<Prop> props;

    public static class Prop<T>{

        private String name;

        @ExpressedValue
        private T value;

        private T type;

        private List<Prop> props;

        public List<Prop> getProps() {
            return props;
        }

        public void setProps(List<Prop> props) {
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

        public T getType() {
            return type;
        }

        public void setType(T type) {
            this.type = type;
        }
    }


    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public List<Prop> getProps() {
        return props;
    }

    public void setProps(List<Prop> props) {
        this.props = props;
    }
}
