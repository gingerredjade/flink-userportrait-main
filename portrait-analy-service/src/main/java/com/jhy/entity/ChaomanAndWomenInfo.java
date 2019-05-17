package com.jhy.entity;

import java.util.List;

/**
 * 潮男潮女信息实体类
 * Created by JHy on 2019/04/25
 */
public class ChaomanAndWomenInfo {
    private String chaotype;            	// 1：潮男；2：潮女
    private String userid;              	// 用户id
    private long count;                 	// 数量
    private String groupbyfield;        	// 分组字段

    private List<ChaomanAndWomenInfo> list;	//

    public String getChaotype() {
        return chaotype;
    }

    public void setChaotype(String chaotype) {
        this.chaotype = chaotype;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getGroupbyfield() {
        return groupbyfield;
    }

    public void setGroupbyfield(String groupbyfield) {
        this.groupbyfield = groupbyfield;
    }

    public List<ChaomanAndWomenInfo> getList() {
        return list;
    }

    public void setList(List<ChaomanAndWomenInfo> list) {
        this.list = list;
    }
}
