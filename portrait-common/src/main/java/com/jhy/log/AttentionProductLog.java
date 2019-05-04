package com.jhy.log;

import java.io.Serializable;

/**
 * Created by JHy on 2019/04/26.
 */
public class AttentionProductLog implements Serializable{
     private int productid;         //商品id
     private int producttypeid;     //商品类别id
     private String opertortime;    //操作时间
     private int operatortype;      //操作类型，0、关注，1、取消
     private String staytime;       //停留时间
     private int userid;            //用户id
     private int usetype;           //终端类型：0、pc端；1、移动端；2、小程序端'
     private String ip;             // 用户ip
     private String brand;          //品牌

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
