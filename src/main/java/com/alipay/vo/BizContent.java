package com.alipay.vo;

/**
 * BizContent
 *
 * @author Administrator
 * @date 2020/4/9
 */
public class BizContent {
    // 商户端的唯一订单号,唯一
    private String out_biz_no;
    // 订单总金额，单位为元，精确到小数点后两位
    private double trans_amount;
    // 业务产品码
    private String product_code;
    // 业务场景
    private String biz_scene;
    // 转账业务的标题
    private String order_title;
    // B2C现金红包、单笔无密转账到支付宝/银行卡不需要该参数
    private String original_order_id;

    // 参与方的唯一标识
    private String identity;
    // 参与方的标识类型
    private String identity_type;
    // 参与方真实姓名
    private String name;

    // 业务备注
    private String remark;
    // 子业务场景，红包业务必传
    private String sub_biz_scene;

    public String getOut_biz_no() {
        return out_biz_no;
    }

    public void setOut_biz_no(String out_biz_no) {
        this.out_biz_no = out_biz_no;
    }

    public double getTrans_amount() {
        return trans_amount;
    }

    public void setTrans_amount(double trans_amount) {
        this.trans_amount = trans_amount;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getBiz_scene() {
        return biz_scene;
    }

    public void setBiz_scene(String biz_scene) {
        this.biz_scene = biz_scene;
    }

    public String getOrder_title() {
        return order_title;
    }

    public void setOrder_title(String order_title) {
        this.order_title = order_title;
    }

    public String getOriginal_order_id() {
        return original_order_id;
    }

    public void setOriginal_order_id(String original_order_id) {
        this.original_order_id = original_order_id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getIdentity_type() {
        return identity_type;
    }

    public void setIdentity_type(String identity_type) {
        this.identity_type = identity_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSub_biz_scene() {
        return sub_biz_scene;
    }

    public void setSub_biz_scene(String sub_biz_scene) {
        this.sub_biz_scene = sub_biz_scene;
    }

    public String toBizContentStringAll(){
        return "{" +
                "\"out_biz_no\":\"" + out_biz_no + "\"," +
                "\"trans_amount\":" + trans_amount + "," +
                "\"product_code\":\"" + product_code + "\"," +
                "\"biz_scene\":\"" + biz_scene + "\"," +
                "\"order_title\":\"" + order_title + "\"," +
                "\"original_order_id\":\"" + original_order_id + "\"," +
                "\"payee_info\":{" +
                "\"identity\":\"" + identity + "\"," +
                "\"identity_type\":\"" + identity_type + "\"," +
                "\"name\":\"" + name + "\"" +
                "    }," +
                "\"remark\":\"" + remark + "\"," +
                "\"business_params\":{\"sub_biz_scene\":\"" + sub_biz_scene + "\"}\"" +
                "}";
    }
    // 0.单笔转账到支付宝账户
    public String doSingleTransfer(){
        return "{" +
                "\"out_biz_no\":\"" + out_biz_no + "\"," +
                "\"trans_amount\":" + trans_amount + "," +
                "\"product_code\":\"" + product_code + "\"," +
                "\"biz_scene\":\"" + biz_scene + "\"," +
                "\"order_title\":\"" + order_title + "\"," +
                "\"payee_info\":{" +
                "\"identity\":\"" + identity + "\"," +
                "\"identity_type\":\"" + identity_type + "\"," +
                "\"name\":\"" + name + "\"" +
                "    }," +
                "\"remark\":\"" + remark + "\"" +
                "}";
    }
    // 1.转账业务单据查询
    public String queryTransferDocument(){
        return "{" +
                "\"out_biz_no\":\"" + out_biz_no + "\"," +
                "\"product_code\":\"" + product_code + "\"," +
                "\"biz_scene\":\"" + biz_scene + "\"" +
                "}";
    }
}
