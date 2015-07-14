package com.test.biz.base;

import com.gzpay.common.rpc.order.OrderBase;
import com.gzpay.common.rpc.order.OrderCheckException;
import com.gzpay.common.rpc.result.ResultBase;
import com.gzpay.common.rpc.result.Status;
import com.test.biz.context.ContextHolder;
import com.test.biz.context.ServiceContext;
import com.test.biz.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 服务抽象
 *  统一日志处理
 *  统一异常处理
 *  xxxxx
 * Created by zyinyan on 2015/7/13.
 */
public abstract class BaseService implements Named{

    @Autowired
    @Qualifier("transactionTemplate")
    TransactionTemplate transactionTemplate;

    //通过实现类的名称来获取日志名称
    Logger logger  = LoggerFactory.getLogger(getName());

    protected <R extends ResultBase, O extends OrderBase, T extends ServiceTemplate>
        R process(O o, final T t){
        logger.info("--进入服务:{},参数:{}--",getName(),o);
        //初始化上下文
        t.initContext();
        R r = (R) ContextHolder.getInstance().getContext().getR();
        try {
            //参数校验
            t.orderCheck(o);

            //服务
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    try {
                        t.service();
                    } catch (ServiceException e) {
                        status.setRollbackOnly();
                        throw e;
                    }
                }
            });

            //正常结果封装
            t.convertResult();

        } catch (Exception e) {
            //参数校验异常
            convertExceptionResult(e);
        } finally {
            r =  (R)ContextHolder.getInstance().getContext().getR();

            //资源清理
            //1. 清理上下文
            destoryContext();
        }

        logger.info("--结束服务:{},结果:{}--",getName(),r);
        return r ;
    }

    /**
     * 异常结果转换
     * @param e
     */
    private void convertExceptionResult(Exception e) {
        ServiceContext context =  ContextHolder.getInstance().getContext();
        context.getR().setStatus(Status.FAIL);
        context.getR().setDescription(e.getMessage());

        if(e instanceof OrderCheckException) {
            context.getR().setCode("xxxCode");
        }else if(e instanceof ServiceException){
            //context.getR().setCode(((ServiceException) e).getCustomerResultEnum().getCode());
        }else{
            //context.getR().setCode(CustomerResultEnum.UNKOWN_EXCEPTION.getCode());
        }
    }

    private void destoryContext() {
        ContextHolder.getInstance().destory();
    }

}
