package com.test.java.biz.api.impl;

import com.gzpay.common.rpc.order.OrderCheckException;
import com.gzpay.common.rpc.result.Status;
import com.test.biz.base.BaseService;
import com.test.biz.base.ServiceTemplate;
import com.test.biz.context.ContextHolder;
import com.test.biz.context.ServiceContext;
import com.test.biz.exceptions.ServiceException;
import com.test.java.facade.api.demoservice.DemoService;
import com.test.java.facade.api.demoservice.DemoServiceOrder;
import com.test.java.facade.api.demoservice.DemoServiceResult;
import com.test.java.facade.api.demoservice.exceptions.DemoServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zyinyan on 2015/6/26.
 */
public class DemoServiceImpl extends BaseService implements DemoService {

    @Override
    public DemoServiceResult demo(final DemoServiceOrder order) throws DemoServiceException {

        return process(order, new ServiceTemplate<DemoServiceOrder,DemoServiceResult,ServiceContext<DemoServiceOrder,DemoServiceResult>>(){

            @Override
            protected void initContext() {
                ServiceContext<DemoServiceOrder,DemoServiceResult> context = new ServiceContext<DemoServiceOrder, DemoServiceResult>();
                context.setO(order);
                DemoServiceResult result = new DemoServiceResult();
                context.setR(result);

                ContextHolder.getInstance().initContext(context);
            }

            @Override
            protected void businessCheck(DemoServiceOrder demoServiceOrder) throws OrderCheckException {

            }

            @Override
            protected void service() throws ServiceException {

            }

            @Override
            protected void businessResultAppend() {
                ServiceContext<DemoServiceOrder,DemoServiceResult> context =  ContextHolder.getInstance().getContext();
                context.getR().setOrderNo(order.getOrderNo());
            }
        });

    }

    @Override
    public String getName() {
        return getClass().getName();
    }
}
