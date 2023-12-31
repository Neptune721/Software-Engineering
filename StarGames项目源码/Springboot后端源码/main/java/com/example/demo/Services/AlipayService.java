package com.example.demo.service;

/**
 * @author zhangzhixi
 * @version 1.0
 * @date 2021-8-19 15:04
 */
public interface AlipayService {

    /**
     * @description: 支付宝电脑网页支付
     * @param subject: 订单名称
     * @param total: 金额
     * @date: 2020/11/3
     * @return java.lang.String
     */
    String page(String subject, String total);

    /**
     * @description: 支付宝手机网页支付
     * @param subject: 订单名称
     * @param total: 金额
     * @date: 2020/11/3
     * @return java.lang.String
     */
    String wap(String subject, String total);

    /**
     * @description: 支付宝退款
     * @param outTradeNo: 商家订单号
     * @param refundAmount: 退款金额(不能大于交易金额)
     * @date: 2020/11/3
     * @return java.lang.String
     */
    String refund(String outTradeNo, String refundAmount);
}
