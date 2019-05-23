package com.jhy.entity;

/**
 * 性别预测模型实体类
 *
 * Created by JHy on 2019/04/25
 */
public class SexPreInfo {

    /**
     * 用户id 订单次数 订单频次 浏览男装
     * 浏览小孩 浏览老人 浏览女士 订单平均金额 浏览商品频次 标签
     */
    private int userid;         // 用户编号
    private long ordernum;      // 订单的总数
    private long orderfre;      // 隔多少天下单
    private int manclothes;     // 浏览男装次数
    private int womenclothes;   // 浏览女装的次数
    private int childclothes;   // 浏览小孩衣服的次数
    private int oldmanclothes;  // 浏览老人的衣服的次数
    private double avramount;   // 订单平均金额
    private int producttimes;   // 每天浏览商品数
    private int label;          // 男，女

    private String groupfield;	// 分组字段

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public long getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(long ordernum) {
        this.ordernum = ordernum;
    }

    public long getOrderfre() {
        return orderfre;
    }

    public void setOrderfre(long orderfre) {
        this.orderfre = orderfre;
    }

    public int getManclothes() {
        return manclothes;
    }

    public void setManclothes(int manclothes) {
        this.manclothes = manclothes;
    }

    public int getWomenclothes() {
        return womenclothes;
    }

    public void setWomenclothes(int womenclothes) {
        this.womenclothes = womenclothes;
    }

    public int getChildclothes() {
        return childclothes;
    }

    public void setChildclothes(int childclothes) {
        this.childclothes = childclothes;
    }

    public int getOldmanclothes() {
        return oldmanclothes;
    }

    public void setOldmanclothes(int oldmanclothes) {
        this.oldmanclothes = oldmanclothes;
    }

    public double getAvramount() {
        return avramount;
    }

    public void setAvramount(double avramount) {
        this.avramount = avramount;
    }

    public int getProducttimes() {
        return producttimes;
    }

    public void setProducttimes(int producttimes) {
        this.producttimes = producttimes;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public String getGroupfield() {
        return groupfield;
    }

    public void setGroupfield(String groupfield) {
        this.groupfield = groupfield;
    }
}
