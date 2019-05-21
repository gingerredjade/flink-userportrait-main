package com.jhy.entity;

/**
 * 分析结果通用实体类
 * Created by JHy on 2019/04/19.
 */
public class AnalyResult {
    private String info;        // 分组条件
    private Long count;         // 总数

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
