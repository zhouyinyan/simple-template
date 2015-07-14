package com.test.biz.context;

import com.gzpay.common.lang.ParameterHolderSupport;
import com.gzpay.common.rpc.order.OrderBase;
import com.gzpay.common.rpc.result.ResultBase;

/**
 *
 * 上下文
 *
 * Created by zyinyan on 2015/7/13.
 */
public class ServiceContext<O extends OrderBase, R extends ResultBase> extends ParameterHolderSupport{

    private O o ;

    private R r;

    public O getO() {
        return o;
    }

    public void setO(O o) {
        this.o = o;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

}
