package com.test.java.facade.api.demoservice;

import com.gzpay.common.rpc.order.OrderBase;

/**
 * Created by zyinyan on 2015/6/26.
 */
public class DemoServiceOrder extends OrderBase{
    private static final long serialVersionUID = 4923583019624572547L;

    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DemoServiceOrder{");
        sb.append("orderNo='").append(orderNo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
