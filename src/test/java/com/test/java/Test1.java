package com.test.java;

import com.ele007.engine.touch.process.TreeStructProcess;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/3/9 16:22
 */

public class Test1 {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.refresh();
        context.start();

        TreeStructProcess iProcessor = (TreeStructProcess) context.getBean("mathCalculaProcess");
//        iProcessor.processCacula("");
//        List<IProcessor> iProcessor = (List<IProcessor>) context.getBean("IProcessor");
//        iProcessor.stream().forEach(item -> {
//            item.processCacula("");
//        });
    }




}
