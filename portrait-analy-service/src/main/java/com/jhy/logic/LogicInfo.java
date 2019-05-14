package com.jhy.logic;

/**
 * 逻辑回归实体类
 * 		最终返回的是这个实体类
 *
 * Created by JHy on 2019/05/14
 */
public class LogicInfo {

	private String variable1; 		// 变量1（维度）
	private String variable2;		// 变量2（维度）
	private String variable3;		// 变量3（维度）
	private String labase;			// 标签
	private String groupbyfield;	// 分组字段

	public String getGroupbyfield() {
		return groupbyfield;
	}

	public void setGroupbyfield(String groupbyfield) {
		this.groupbyfield = groupbyfield;
	}

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

	public String getLabase() {
		return labase;
	}

	public void setLabase(String labase) {
		this.labase = labase;
	}


}
