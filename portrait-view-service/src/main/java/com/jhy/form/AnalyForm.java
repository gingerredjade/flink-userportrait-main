package com.jhy.form;

/**
 * 分析请求参数表单对象实体类
 *
 * Created by JHy on 2019/05/16.
 */
public class AnalyForm {
    private String type;
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
