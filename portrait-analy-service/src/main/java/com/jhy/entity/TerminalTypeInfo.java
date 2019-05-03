package com.jhy.entity;

/**
 * 终端偏好实体类
 * Created by JHy on 2019/04/25
 */
public class TerminalTypeInfo {
    private String terminaltype;         // 用户类型
    private long count;             // 数量
    private String groupbyfield;    // 分组。。。。

    public String getUsetype() {
        return terminaltype;
    }

    public void setUsetype(String terminaltype) {
        this.terminaltype = terminaltype;
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
}
