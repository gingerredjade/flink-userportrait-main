package com.jhy.entity;

/**
 * 年代标签实体类
 * Created by JHy on 2019/04/25
 */
public class YearBase {

    /*
    这里未来会是做增量统计的
     */

    private String yeartype;    // 年代类型
    private Long count;         // 数量
    private String groupfield;  //分组字段，避免后面的字符串与年代冲突

    public String getYeartype() {
        return yeartype;
    }

    public void setYeartype(String yeartype) {
        this.yeartype = yeartype;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getGroupfield() {
        return groupfield;
    }

    public void setGroupfield(String groupfield) {
        this.groupfield = groupfield;
    }
}
