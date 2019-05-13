package com.jhy.log;

import java.io.Serializable;

/**
 * 用户行为日志结构以及实体定义-关注商品行为实体
 *
 * Created by JHy on 2019/04/26.
 */
public class AttentionProductLog implements Serializable{
     private int productid;         // 商品id
     private int producttypeid;     // 商品类别id
     private String opertortime;    // 操作时间
     private int operatortype;      // 操作类型，0、关注；1、取消
     private String staytime;       // 停留时间
     private int userid;            // 用户id
     private int terminaltype;      // 终端类型：0、pc端；1、移动端；2、小程序端'
     private String ip;             // 用户ip
     private String brand;          // 品牌

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getProducttypeid() {
        return producttypeid;
    }

    public void setProducttypeid(int producttypeid) {
        this.producttypeid = producttypeid;
    }

    public String getOpertortime() {
        return opertortime;
    }

    public void setOpertortime(String opertortime) {
        this.opertortime = opertortime;
    }

    public int getOperatortype() {
        return operatortype;
    }

    public void setOperatortype(int operatortype) {
        this.operatortype = operatortype;
    }

    public String getStaytime() {
        return staytime;
    }

    public void setStaytime(String staytime) {
        this.staytime = staytime;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getTerminaltype() {
        return terminaltype;
    }

    public void setTerminaltype(int terminaltype) {
        this.terminaltype = terminaltype;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
