package com.ele007.engine.factory;

import com.ele007.engine.touch.process.IProcessor;
import com.ele007.engine.utils.AuxiliaryLoadUtil;
import com.ele007.engine.utils.RequstEnum;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/3/9 19:04
 */
public class CalculaProcessorFactory<T> {
    private static List<IProcessor> iProcessors = new LinkedList<>();
    private static CalculaProcessorFactory processorFactory = new CalculaProcessorFactory();
    static {
        initProcessor();
    }

    private CalculaProcessorFactory() {
    }

    private static synchronized List<IProcessor> initProcessor(){
        List<Class> clazzs = AuxiliaryLoadUtil.getAllInterfaceAchieveClass(IProcessor.class);
        for(Class clazz : clazzs){
            try {
                IProcessor iProcessor = (IProcessor) clazz.newInstance();
                iProcessors.add(iProcessor);
                System.out.println("加载实现类:"+iProcessor.getClass());
            } catch (Exception e) {
                throw new RuntimeException("BuildProcessFactory init err",e);
            }
        }
        return iProcessors;
    }

    public IProcessor createProcessor(T t, RequstEnum structType){
        return iProcessors.stream()
                .filter(i -> i.isSupport(t, structType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("BuildProcessFactory 没有找到对应处理器，请检查数据结构,关键注解：@ExpressedProps，参考：com.ele007.engine.entity.Target"));
    }

    public static List<IProcessor> getiProcessors(){
        return iProcessors;
    }

    public static CalculaProcessorFactory getProcessorFactory() {
        return processorFactory;
    }
}
