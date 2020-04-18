package com.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.alipay.config.AlipayConfig;
import com.alipay.vo.BizContent;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LoginController
 *
 * @author Administrator
 * @date 2020/4/11
 */
@Controller
public class PageController {

    // 主页
    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    // Alipay
    @RequestMapping("index")
    public String goIndex(Model model) {
        return "index";
    }

    // 付款
    @RequestMapping("alipayMentPage")
    public String goAlipayMentPage() {
        return "alipay.trade.page.pay";
    }

    @RequestMapping("alipayMent")
    public @ResponseBody
    String goAlipayMent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO 参数可以定义实体类 BizContent 接收
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        //付款金额，必填
        String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"), "UTF-8");
        //订单名称，必填
        String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"), "UTF-8");
        //订单描述，可空
        String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"), "UTF-8");

        String bizContent = "{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}";

        return AlipayConfig.doAlipayMent(bizContent);
    }

    // 交易查询
    @RequestMapping("tradeQueryPage")
    public String goTradeQueryPage() {
        return "alipay.trade.query";
    }

    @RequestMapping("tradeQuery")
    public @ResponseBody
    String goTradeQuery(HttpServletRequest request) throws Exception {
        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(request.getParameter("WIDTQout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("WIDTQtrade_no").getBytes("ISO-8859-1"), "UTF-8");
        //请二选一设置

        String bizContent = "{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"trade_no\":\"" + trade_no + "\"}";

        return AlipayConfig.doTradeQuery(bizContent);
    }

    // 退款
    @RequestMapping("tradeRefundPage")
    public String goTradeRefundPage() {
        return "alipay.trade.refund";
    }

    @RequestMapping("tradeRefund")
    public @ResponseBody
    String goTradeRefund(HttpServletRequest request) throws Exception {
        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(request.getParameter("WIDTRout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("WIDTRtrade_no").getBytes("ISO-8859-1"), "UTF-8");
        //请二选一设置
        //需要退款的金额，该金额不能大于订单金额，必填
        String refund_amount = new String(request.getParameter("WIDTRrefund_amount").getBytes("ISO-8859-1"), "UTF-8");
        //退款的原因说明
        String refund_reason = new String(request.getParameter("WIDTRrefund_reason").getBytes("ISO-8859-1"), "UTF-8");
        //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
        String out_request_no = new String(request.getParameter("WIDTRout_request_no").getBytes("ISO-8859-1"), "UTF-8");

        String bizContent = "{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"trade_no\":\"" + trade_no + "\","
                + "\"refund_amount\":\"" + refund_amount + "\","
                + "\"refund_reason\":\"" + refund_reason + "\","
                + "\"out_request_no\":\"" + out_request_no + "\"}";

        return AlipayConfig.doTradeRefund(bizContent);
    }


    // 退款查询
    @RequestMapping("tradeRefundQueryPage")
    public String goTradeRefundQueryPage() {
        return "alipay.trade.fastpay.refund.query";
    }

    @RequestMapping("tradeRefundQuery")
    public @ResponseBody
    String goTradeRefundQuery(HttpServletRequest request) throws Exception {
        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(request.getParameter("WIDRQout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("WIDRQtrade_no").getBytes("ISO-8859-1"), "UTF-8");
        //请二选一设置
        //请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号，必填
        String out_request_no = new String(request.getParameter("WIDRQout_request_no").getBytes("ISO-8859-1"), "UTF-8");

        String bizContent = "{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"trade_no\":\"" + trade_no + "\","
                + "\"out_request_no\":\"" + out_request_no + "\"}";

        return AlipayConfig.doTradeRefundQuery(bizContent);
    }

    // 交易关闭
    @RequestMapping("tradeClosePage")
    public String goTradeClosePage() {
        return "alipay.trade.close";
    }

    @RequestMapping("tradeClose")
    public @ResponseBody
    String goTradeClose(HttpServletRequest request) throws Exception {
        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(request.getParameter("WIDTCout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("WIDTCtrade_no").getBytes("ISO-8859-1"), "UTF-8");
        //请二选一设置

        String bizContent = "{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"trade_no\":\"" + trade_no + "\"}";

        return AlipayConfig.doTradeClose(bizContent);
    }


    public static void main(String[] args) {
        BizContent bizContent = new BizContent();
        bizContent.setOut_biz_no("789456201806300003");//订单号
        bizContent.setTrans_amount(0.01);
        bizContent.setProduct_code(AlipayConfig.ALIPAY_PRODUCT_CODE);
        bizContent.setBiz_scene(AlipayConfig.ALIPAY_BIZ_SCENE);
        bizContent.setOrder_title("支付");
        // bizContent.setOriginal_order_id(null);
        bizContent.setIdentity("");//收款人支付宝登陆号
        bizContent.setIdentity_type(AlipayConfig.ALIPAY_IDENTITY_TYPE);
        bizContent.setName("沙箱环境");//收款人真实姓名
        bizContent.setRemark("单笔转账到支付宝账户");
        // bizContent.setSub_biz_scene(null);

        try {
            AlipayFundTransUniTransferResponse response = AlipayConfig.doSingleTransfer(bizContent);
            // AlipayFundTransCommonQueryResponse response = AlipayUtil.queryTransferDocument(bizContent);
            if (response.isSuccess()) {
                System.out.println("请求成功");
                System.out.println(response.getBody());
                System.out.println("-----------------------------------------");
                if ("10000".equals(response.getCode())) {
                    System.out.println(response.getMsg());
                } else if ("20000".equals(response.getCode())) {
                    System.out.println(response.getSubMsg());
                }
            } else {
                System.out.println("请求失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

    }


}
