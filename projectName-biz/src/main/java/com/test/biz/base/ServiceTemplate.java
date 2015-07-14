package com.test.biz.base;

/**
 *
 * 服务模板
 *
 * Created by zyinyan on 2015/7/13.
 */

import com.gzpay.common.rpc.order.OrderBase;
import com.gzpay.common.rpc.order.OrderCheckException;
import com.gzpay.common.rpc.result.ResultBase;
import com.gzpay.common.rpc.result.Status;
import com.test.biz.context.ContextHolder;
import com.test.biz.context.ServiceContext;
import com.test.biz.exceptions.ServiceException;

public  abstract class  ServiceTemplate<O extends OrderBase, R extends ResultBase, C extends ServiceContext<O,R>> {

    /**
     * 必须初始化结果
     */
    protected abstract void initContext();

    //参数校验
    protected void orderCheck(O o) throws OrderCheckException{
        if(null == o){
            OrderCheckException e = new OrderCheckException();
            e.addError("order","不能为null");
            throw e;
        }
        o.check();
        businessCheck(o);
    }

    //特殊业务参数校验
    protected abstract void businessCheck(O o) throws OrderCheckException;

    //服务处理
    //服务中间结果，保存在上下文中
    protected abstract void service() throws ServiceException;

    //正常结果封装
    protected  void convertResult(){
        ServiceContext context =  ContextHolder.getInstance().getContext();
        context.getR().setStatus(Status.SUCCESS);
        context.getR().setDescription(Status.SUCCESS.getMessage());
        context.getR().setCode(Status.SUCCESS.getCode());
        businessResultAppend();
    }

    //业务结果添加
    protected abstract void businessResultAppend();

}
