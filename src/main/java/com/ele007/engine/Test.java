package com.ele007.engine;

import com.alibaba.fastjson.JSON;
import com.ele007.engine.entity.MockData;
import com.ele007.engine.entity.MapTarget;
import com.ele007.engine.entity.Target;
import com.ele007.engine.entity.TreeTarget;
import com.ele007.engine.touch.HandleExpressionTemplate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/3/9 10:34
 */
public class Test {


    public <T> T test (T t) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        //得到属性
//        Object obj = t.getClass().newInstance();
        Field[] declaredFields = t.getClass().getDeclaredFields();
        for (Field field :  declaredFields) {
            field.setAccessible(true);
            if (field.getName().equals("expression")) {
                System.out.println(field.get(t));
            }
            if (field.getType() != String.class) {

                System.out.println(field.get(t));
            }
        }


        return t;
    }

    public static void main(String[] args) {
        HandleExpressionTemplate template = new HandleExpressionTemplate();
//        template.process(new ConstantUtils(), ConstantUtils.class);
        //示例1：String
        Integer integer = template.process("1+1+1", Integer.class);
        System.out.println("传入String对象结果：" + integer);

        //示例2：传入对象或传入tree结构数据

        Target bean = JSON.parseObject(MockData.str2, Target.class);
        Float process = template.process(bean, Float.class);
        System.out.println("传入普通对象结果：" + process);

        TreeTarget treeTarget = JSON.parseObject(MockData.str, TreeTarget.class);
        BigDecimal decimal = template.process(treeTarget, BigDecimal.class);
        System.out.println("传入tree对象结果:" +decimal);

        //示例3：传入map
        HashMap map = new HashMap();
        HashMap map1 = new HashMap();
        map.put("姓名", "张三");
        map.put("性别", "男");
        map.put("年龄", "25");

        map1.put("map1", map);
        MapTarget mapTarget = new MapTarget();
        mapTarget.setProps(map1);
        mapTarget.setExpression("\"张三\".equals(map1.get(\"姓名\"))");

        boolean b = template.process(mapTarget, Boolean.class);
        System.out.println("传入Map对象结果:" + b);
    }
}
