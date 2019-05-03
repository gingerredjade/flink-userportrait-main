package com.jhy.entity;

/**
 * 品牌偏好实体类
 * Created by JHy on 2019/04/25
 */
public class BrandLike {
    private String brand;
    private long count;
    private String groupbyfield;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
