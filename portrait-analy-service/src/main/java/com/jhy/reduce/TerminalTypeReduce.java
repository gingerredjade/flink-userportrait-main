package com.jhy.reduce;

import com.jhy.entity.TerminalTypeInfo;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * 用户终端偏好标签Reduce
 *      统计下这些用户的终端偏好
 *
 * Created by JHy on 2019/05/13
 */
public class TerminalTypeReduce implements ReduceFunction<TerminalTypeInfo> {
	@Override
	public TerminalTypeInfo reduce(TerminalTypeInfo terminalTypeInfo, TerminalTypeInfo t1) throws Exception {
		String terminaltype = terminalTypeInfo.getTerminaltype();
		Long count1 = terminalTypeInfo.getCount();

		Long count2 = t1.getCount();

		TerminalTypeInfo terminalTypeInfoFinal = new TerminalTypeInfo();
		terminalTypeInfoFinal.setTerminaltype(terminaltype);
		terminalTypeInfoFinal.setCount(count1+count2);
		return terminalTypeInfoFinal;
	}
}
