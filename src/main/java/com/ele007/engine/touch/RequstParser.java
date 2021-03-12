package com.ele007.engine.touch;

import com.alibaba.fastjson.JSON;
import com.ele007.engine.annotation.Expressed;
import com.ele007.engine.annotation.ExpressedProps;
import com.ele007.engine.annotation.ExpressedValue;
import com.ele007.engine.utils.ConstantUtils;
import com.ele007.engine.utils.RequstEnum;
import com.ele007.engine.utils.ThreadLocalUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 默认String类型
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/3/9 14:59
 */
public class RequstParser {

    public static RequstEnum parseExpressedPropsValue(Object t){
        Field[] fields = t.getClass().getDeclaredFields();
        if (fields.length < 1) {
            return RequstEnum.STRING;
        }
        if (t.getClass() == String.class){
            return RequstEnum.STRING;
        }
        String expressedFieldName = "";
        String expressedPropsFieldName = "";
        for (Field field : fields) {
            field.setAccessible(true);
            Expressed expressed = field.getAnnotation(Expressed.class);
            ExpressedProps expressedProps = field.getAnnotation(ExpressedProps.class);
            if (null != expressed) {
                expressedFieldName = field.getName();
            }
            if (null != expressedProps) {
                expressedPropsFieldName = field.getName();
                ThreadLocalUtil.set(ConstantUtils.EXPRESSION, expressedFieldName);
                ThreadLocalUtil.set(ConstantUtils.EXPRESSION_PROPS, expressedPropsFieldName);
                RequstEnum anEnum = expressedProps.struct();
                if (null == anEnum) {
                    return RequstEnum.parseClass(field.getType());
                }
                return anEnum;
            }
        }
        return RequstEnum.NULL;
    }

    /**
     * 获取真实表达式
     * @param o
     * @return
     */
    public static String realExpreesion (Object o){
        try {
//            if (o instanceof String) {
//                return (String)o;
//            }
//            String expressedFieldName = ThreadLocalUtil.get(ConstantUtils.EXPRESSION);
//            String expressedPropsFieldName = ThreadLocalUtil.get(ConstantUtils.EXPRESSION_PROPS);
//
//            String expressionTemplate = getValueByFieldName(o, expressedFieldName);
//            Object props = getValueByFieldName(o, expressedPropsFieldName);
//            if (props instanceof Map) {
//                return expressionTemplate;
//            }
//            System.out.println(JSON.toJSONString(props));
//            List<String> changeValues = parseChangeValue(expressionTemplate);
//            HashMap<String, String> templToValueMap = templToValueMap(changeValues, props);
//            Set<Map.Entry<String, String>> entries = templToValueMap.entrySet();
//            for (Map.Entry<String, String> entry : entries) {
//                expressionTemplate = expressionTemplate.replace(entry.getKey(), entry.getValue());
//            }
//            System.out.println(expressionTemplate);
//            String expression = expressionTemplate.replace("{", "").replace("}", "");
//            System.out.println(expression);
//            return expression;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("");
        } finally {
            ThreadLocalUtil.remove();
        }
        return null;
    }

    public static String getExpreesionTemplate (Object o){
        if (o instanceof String) {
            return (String)o;
        }
        String expressedFieldName = ThreadLocalUtil.get(ConstantUtils.EXPRESSION);
        return getValueByFieldName(o, expressedFieldName);
    }

    public static <T> T getExpreesionProps (Object o){
        String expressedPropsFieldName = ThreadLocalUtil.get(ConstantUtils.EXPRESSION_PROPS);
         return getValueByFieldName(o, expressedPropsFieldName);
    }

    public static String reductionExpressionByList (String expressionTemplate, Object props) {
        List<String> changeValues = parseChangeValue(expressionTemplate);
        HashMap<String, String> templToValueMap = templToValueMap(changeValues, props);
        Set<Map.Entry<String, String>> entries = templToValueMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            expressionTemplate = expressionTemplate.replace(entry.getKey(), entry.getValue());
        }
        System.out.println(expressionTemplate);
        String expression = expressionTemplate.replace("{", "").replace("}", "");
        System.out.println(expression);
        return expression;
    }

    public static String reductionExpressionByBean (String expressionTemplate, Object props) {
        List<String> changeValues = parseChangeValue(expressionTemplate);
        HashMap<String, String> templToValueMap = templToValueMap(changeValues, props);
        Set<Map.Entry<String, String>> entries = templToValueMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            expressionTemplate = expressionTemplate.replace(entry.getKey(), entry.getValue());
        }
        System.out.println(expressionTemplate);
        String expression = expressionTemplate.replace("{", "").replace("}", "");
        System.out.println(expression);
        return expression;
    }

    private static <T> T getValueByFieldName(Object o, String fieldName){
        try {
            Field[] fields = o.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals(fieldName)) {
                    Object value = field.get(o);
                    return (T)value;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("根据字段名获取值失败,fieldName:" + fieldName);
        }
        throw new RuntimeException("没有找到字段：" + fieldName);
    }

    /**
     * 解析计价公式，解析出可变值名称-》属性名
     * @param formula
     * @return
     */
    private static List<String> parseChangeValue(String formula){
        List<String> attrNames = new ArrayList<>();
        String p1="\\{[^\\{\\}]{1,}\\}";
        String p2="\\{\\([^\\{\\}}]{1,}\\)\\}";
        Pattern p=Pattern.compile(p1);
        Matcher m=p.matcher(formula);
        while(m.find()){
            attrNames.add(m.group().substring(1, m.group().length() -1));
        }
        return attrNames;
    }

    private static HashMap<String, String> templToValueMap(List<String> changeValues, Object props){
        HashMap<String, String> map = new HashMap<>();
        for (String wood : changeValues) {
            String[] split = wood.split("\\.");
//            List child = props;
            Object child = props;
            for (int i = 1; i <= split.length; i++) {
                String keyword = split[i -1];
                if (i - 1 < 1) {
                    continue;
                }
                HashMap<String, Object> targetValue = targetValue(keyword, child);
                if (i == split.length) {
                    // 理论只有一个元素
                    String expressedValueFieldName = ThreadLocalUtil.get(ConstantUtils.EXPRESSION_VALUE);
                    if (StringUtils.isBlank(expressedValueFieldName)) {
                        throw new RuntimeException("没有找到表达式字段对应值，请检查表达式字段的对应值上加入注解：@ExpressedValue");
                    }
                    String value = getValueByFieldName(targetValue.get(keyword), expressedValueFieldName).toString();
                    map.put(wood, value);
                }
                String expressedPropsFieldName = ThreadLocalUtil.get(ConstantUtils.EXPRESSION_PROPS);
                child = getValueByFieldName(targetValue.get(keyword), expressedPropsFieldName);
            }
        }
        return map;
    }

    public static HashMap<String, Object> targetValue(String keyword, Object prop){
        HashMap<String, Object> targetObj = new HashMap<>();

        if (prop instanceof List) {
            List props = (List) prop;
            for (Object obj : props) {
                HashMap<String, Object> map = test(keyword, obj);
                targetObj.putAll(map);
            }
        } else {
            HashMap<String, Object> map = test(keyword, prop);
            targetObj.putAll(map);
        }
        if (CollectionUtils.isEmpty(targetObj)) {
            throw new RuntimeException(JSON.toJSONString(prop) + " 内找不到关键字: " + keyword);
        }
        return targetObj;
    }

    public static HashMap<String, Object> test(String keyword, Object obj){
        HashMap<String, Object> targetObj = new HashMap<>();
        try{
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                ExpressedValue expressedValue = field.getAnnotation(ExpressedValue.class);
                if (null != expressedValue) {
                    ThreadLocalUtil.set(ConstantUtils.EXPRESSION_VALUE, field.getName());
                }
                Object o = field.get(obj);
                if (o instanceof String && CollectionUtils.isEmpty(targetObj)) {
                    String value = (String)o;
                    if (keyword.equals(value)) {
                        targetObj.put(keyword, obj);
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return targetObj;
    }

}
