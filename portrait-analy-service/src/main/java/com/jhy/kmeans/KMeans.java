package com.jhy.kmeans;

/**
 * KMeans实体类
 * Created by JHy on 2019/5/16.
 */
public class KMeans {
    private String variable1;		// 变量1（点）
    private String variable2;		// 变量2（点）
    private String variable3;		// 变量3（点）
    private String groupbyfield;	// 分组字段

    public String getVariable1() {
        return variable1;
    }

    public void setVariable1(String variable1) {
        this.variable1 = variable1;
    }

    public String getVariable2() {
        return variable2;
    }

    public void setVariable2(String variable2) {
        this.variable2 = variable2;
    }

    public String getVariable3() {
        return variable3;
    }

    public void setVariable3(String variable3) {
        this.variable3 = variable3;
    }

    public String getGroupbyfield() {
        return groupbyfield;
    }

    public void setGroupbyfield(String groupbyfield) {
        this.groupbyfield = groupbyfield;
    }
}
