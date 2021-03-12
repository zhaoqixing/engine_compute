package com.ele007.engine.entity;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/3/9 10:38
 */
public class MockData {

    private String expression;

    private MockData child;


    public static String str2 = "{\n" +
            "\t\t\"expression\": \"{$root.部件1.展开长} * 1000 / 1000 / 1000\",\n" +
            "\t\t\"props\": {\n" +
            "\t\t\t\t\"name\": \"部件1\",\n" +
            "\t\t\t\t\"type\": \"Object\",\n" +
            "\t\t\t\t\"props\":{\n" +
            "\t\t\t\t\t\t\"name\": \"展开长\",\n" +
            "\t\t\t\t\t\t\"value\": 300\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t\t\n" +
            "\t\t\n" +
            "}";


    public static String str1 = "{\n" +
            "\t\t\"expression\": \"{$root.长} * {$root.宽} * {$root.高} / 1000 / 1000 / 1000\",\n" +
            "\t\t\"props\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"name\": \"长\",\n" +
            "\t\t\t\t\"value\": 100\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"name\": \"宽\",\n" +
            "\t\t\t\t\"value\": 200\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"name\": \"高\",\n" +
            "\t\t\t\t\"value\": 80\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t}";



    public static String str = "{\n" +
            "\t\t\"expression\": \"{$root.部件1.展开长} * {$root.部件1.展开宽} * {$root.部件1.材料.克重} / 1000 / 1000 / 1000\",\n" +
            "\t\t\"props\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"name\": \"长\",\n" +
            "\t\t\t\t\"value\": 100\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"name\": \"宽\",\n" +
            "\t\t\t\t\"value\": 200\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"name\": \"高\",\n" +
            "\t\t\t\t\"value\": 80\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"name\": \"部件1\",\n" +
            "\t\t\t\t\"type\": \"Object\",\n" +
            "\t\t\t\t\"props\": [\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"name\": \"展开长\",\n" +
            "\t\t\t\t\t\t\"value\": 300\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"name\": \"展开宽\",\n" +
            "\t\t\t\t\t\t\"value\": 600\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"name\": \"材料\",\n" +
            "\t\t\t\t\t\t\"type\": \"Object\",\n" +
            "\t\t\t\t\t\t\"props\": [\n" +
            "\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\"name\": \"纸型\",\n" +
            "\t\t\t\t\t\t\t\t\"value\": \"铜版纸\"\n" +
            "\t\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\"name\": \"克重\",\n" +
            "\t\t\t\t\t\t\t\t\"value\": \"157.5\"\n" +
            "\t\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\"name\": \"规格\",\n" +
            "\t\t\t\t\t\t\t\t\"value\": \"1194*768\"\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t]\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"name\": \"印刷\",\n" +
            "\t\t\t\t\t\t\"type\": \"Object\",\n" +
            "\t\t\t\t\t\t\"props\": [\n" +
            "\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\"name\": \"正面色数\",\n" +
            "\t\t\t\t\t\t\t\t\"value\": \"4\"\n" +
            "\t\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\"name\": \"正面色数\",\n" +
            "\t\t\t\t\t\t\t\t\"value\": \"4\"\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\n" +
            "\t\t\t\t\t\t]\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t]\n" +
            "\t\t\t\n" +
            "\t\t\t}\n" +
            "\t\t\t\n" +
            "\t\t]\n" +
            "\t}";

    public MockData() {
    }

    public MockData(String expression) {
        this.expression = expression;
    }

    public MockData(String expression, MockData child) {
        this.expression = expression;
        this.child = child;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public MockData getChild() {
        return child;
    }

    public void setChild(MockData child) {
        this.child = child;
    }

}
