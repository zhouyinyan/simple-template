package com.test.java.facade.api.demoservice;

import com.gzpay.common.rpc.result.ResultBase;

/**
 * Created by zyinyan on 2015/6/26.
 */
public class DemoServiceResult extends ResultBase {
    private static final long serialVersionUID = -2328770994861507068L;

    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DemoServiceResult{");
        sb.append("orderNo='").append(orderNo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
