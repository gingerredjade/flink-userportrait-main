package com.jhy.log;

import java.io.Serializable;

/**
 * 用户行为日志结构以及实体定义-浏览商品行为实体
 *
 * Created by JHy on 2019/04/26.
 */
public class ScanProductLog implements Serializable{
     private int productid;             // 商品id
     private int producttypeid;         // 商品类别id
     private String scantime;           // 浏览时间
     private String staytime;           // 停留时间
     private int userid;                // 用户id
     private int usetype;               // 终端类型：0、pc端；1、移动端；2、小程序端'
     private String ip;                 // 用户ip

    private String brand;//品牌

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

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

    public String getScantime() {
        return scantime;
    }

    public void setScantime(String scantime) {
        this.scantime = scantime;
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

    public int getUsetype() {
        return usetype;
    }

    public void setUsetype(int usetype) {
        this.usetype = usetype;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
