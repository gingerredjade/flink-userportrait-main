package com.jhy.entity;

import java.util.List;

/**
 * 败家指数实体类
 *      败家指数定义：败家指数 = 支付金额平均值*0.3、最大支付金额*0.3、下单频率*0.4。
 *      败家指数会有一个时间的限定，比如近1年/2年败家指数的计算。
 *      败家指数需按照用户进行分组聚合的。
 *
 * Created by JHy on 2019/04/25
 */
public class BaiJiaInfo {

    private String baijiatype;      // 败家指数区段：0-20 、20-50 、50-70、70-80、80-90、90-100
    private String userid;
    private String createtime;      // 订单创建时间
    private String amount ;         // 订单金额
    private String paytype ;        // 订单支付类型
    private String paytime;         // 订单支付时间
    private String paystatus;       // 0、未支付 1、已支付 2、已退款
    private String couponamount;    // 使用优惠券金额
    private String totalamount;     // 订单支付总金额
    private String refundamount;    // 退款金额
    private Long count;             // 数量
    private String groupfield;      // 分组

    private List<BaiJiaInfo> list;

    public List<BaiJiaInfo> getList() {
        return list;
    }

    public void setList(List<BaiJiaInfo> list) {
        this.list = list;
    }

    public String getBaijiatype() {
        return baijiatype;
    }

    public void setBaijiatype(String baijiatype) {
        this.baijiatype = baijiatype;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public String getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(String paystatus) {
        this.paystatus = paystatus;
    }

    public String getCouponamount() {
        return couponamount;
    }

    public void setCouponamount(String couponamount) {
        this.couponamount = couponamount;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getRefundamount() {
        return refundamount;
    }

    public void setRefundamount(String refundamount) {
        this.refundamount = refundamount;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getGroupfield() {
        return groupfield;
    }

    public void setGroupfield(String groupfield) {
        this.groupfield = groupfield;
    }
}
