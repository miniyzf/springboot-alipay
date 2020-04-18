package com.alipay.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayFundTransCommonQueryResponse;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.alipay.vo.BizContent;

import java.io.FileWriter;
import java.io.IOException;

/**
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "";
    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8082/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8082/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "UTF-8";
    //
    public static String format = "JSON";
    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    // 支付宝网关
    public static String log_path = "D:\\";


    // 单笔无密转账到支付宝账户
    public final static String ALIPAY_PRODUCT_CODE = "TRANS_ACCOUNT_NO_PWD";
    // B2C现金红包、单笔无密转账到支付宝/银行卡
    public final static String ALIPAY_BIZ_SCENE = "DIRECT_TRANSFER";
    // 支付宝登录号，支持邮箱和手机号格式
    public final static String ALIPAY_IDENTITY_TYPE = "ALIPAY_LOGON_ID";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    private static AlipayClient alipayClient;
    static {
        alipayClient = new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key, format, charset, alipay_public_key, sign_type);
    }

    // 电脑网站支付-付款
    public static String doAlipayMent(String bizContent)throws Exception{
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);
        alipayRequest.setBizContent(bizContent);

        return alipayClient.pageExecute(alipayRequest).getBody();
    }
    // 电脑网站支付-交易查询
    public static String doTradeQuery(String bizContent)throws Exception{
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        alipayRequest.setBizContent(bizContent);
        return alipayClient.execute(alipayRequest).getBody();
    }
    // 电脑网站支付-退款
    public static String doTradeRefund(String bizContent)throws Exception{
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        alipayRequest.setBizContent(bizContent);
        return alipayClient.execute(alipayRequest).getBody();
    }
    // 电脑网站支付-退款查询
    public static String doTradeRefundQuery(String bizContent)throws Exception{
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();
        alipayRequest.setBizContent(bizContent);
        return alipayClient.execute(alipayRequest).getBody();
    }
    // 电脑网站支付-交易关闭
    public static String doTradeClose(String bizContent)throws Exception{
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        alipayRequest.setBizContent(bizContent);
        return alipayClient.execute(alipayRequest).getBody();
    }

    // 单笔无密转账到支付宝账户
    public static AlipayFundTransUniTransferResponse doSingleTransfer(BizContent bizContent)throws AlipayApiException {
        AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
        request.setBizContent(bizContent.doSingleTransfer());

        AlipayFundTransUniTransferResponse response = null;
        response = alipayClient.execute(request);

        return response;
    }

    public static AlipayFundTransCommonQueryResponse queryTransferDocument(BizContent bizContent)throws AlipayApiException{
        AlipayFundTransCommonQueryRequest request = new AlipayFundTransCommonQueryRequest();
        request.setBizContent(bizContent.doSingleTransfer());

        AlipayFundTransCommonQueryResponse response = null;
        response = alipayClient.execute(request);

        return response;
    }

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

