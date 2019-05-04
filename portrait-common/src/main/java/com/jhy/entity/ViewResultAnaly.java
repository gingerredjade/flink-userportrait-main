package com.jhy.entity;

import java.util.List;

/**
 * Created by JHy on 2019/04/19.
 */
public class ViewResultAnaly {
    private List<String> infolist;          //分组list，x轴的值
    private List<Long> countlist;           //数量list
    private String result;
    private String typename;                //标签类型名称
    private String lablevalue;              //标签类型对应的值

    private  List<ViewResultAnaly> list;    //所有标签信息

    public List<ViewResultAnaly> getList() {
        return list;
    }

    public void setList(List<ViewResultAnaly> list) {
        this.list = list;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getLablevalue() {
        return lablevalue;
    }

    public void setLablevalue(String lablevalue) {
        this.lablevalue = lablevalue;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<String> getInfolist() {
        return infolist;
    }

    public void setInfolist(List<String> infolist) {
        this.infolist = infolist;
    }

    public List<Long> getCountlist() {
        return countlist;
    }

    public void setCountlist(List<Long> countlist) {
        this.countlist = countlist;
    }
}
