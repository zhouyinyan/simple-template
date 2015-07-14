package com.test.biz.context;

/**
 * 上下文持有者
 * Created by zyinyan on 2015/7/13.
 */
public class ContextHolder {

    private ContextHolder(){}

    private static final ContextHolder contextHolder = new ContextHolder();

    private static final ThreadLocal contextThreadLocal = new ThreadLocal();

    public static ContextHolder getInstance(){
        return contextHolder;
    }

    public ServiceContext getContext(){
        ServiceContext context = (ServiceContext)contextThreadLocal.get();
        return context;
    }

    public void initContext(ServiceContext context){
        contextThreadLocal.set(context);
    }

    public void destory(){
        contextThreadLocal.remove();
    }
}
