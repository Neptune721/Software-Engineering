package com.example.demo.service.impl;

/**
 * @author zhangzhixi
 * @version 1.0
 * @date 2021-8-19 15:04
 */

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.common.models.AlipayTradeRefundResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.alipay.easysdk.payment.wap.models.AlipayTradeWapPayResponse;
import com.example.demo.service.AlipayService;
import com.example.demo.util.OrderUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AlipayServiceImpl implements AlipayService {

    // 支付成功后要跳转的页面
    @Value("${alipay.returnUrl}")
    private String returnUrl;

    // 支付宝前台手机网页支付中途取消跳转地址
    @Value("${alipay.errorUrl}")
    private String errorUrl;

    @Override
    public String page(String subject, String total) {

        try {
            AlipayTradePagePayResponse response = Factory.Payment
                    // 选择电脑网站
                    .Page()
                    // 调用支付方法(订单名称, 商家订单号, 金额, 成功页面)
                    .pay(subject, OrderUtil.getOrderNo(), total, returnUrl);

            return response.body;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String wap(String subject, String total) {

        try {
            AlipayTradeWapPayResponse response = Factory.Payment
                    //选择手机网站
                    .Wap()
                    // 调用支付方法(订单名称, 商家订单号, 金额, 中途退出页面, 成功页面)
                    .pay(subject, OrderUtil.getOrderNo(), total, errorUrl, returnUrl);

            return response.body;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String refund(String outTradeNo, String refundAmount) {
        try {
            AlipayTradeRefundResponse response = Factory.Payment
                    .Common()
                    // 调用交易退款(商家订单号, 退款金额)
                    .refund(outTradeNo, refundAmount);

            if (response.getMsg().equals("Success")) {
                return "退款成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "退款失败";
    }
}
