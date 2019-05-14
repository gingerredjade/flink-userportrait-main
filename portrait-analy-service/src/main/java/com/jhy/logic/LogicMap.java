package com.jhy.logic;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.Random;

/**
 * 逻辑回归算法Map
 * 		没涉及业务，模仿的，实际要根据业务进行处理！
 *
 * Created by JHy on 2019/04/25
 */
public class LogicMap implements MapFunction<String, LogicInfo> {

	@Override
	public LogicInfo map(String value) throws Exception {
		if(StringUtils.isBlank(value)){
			return null;
		}
		Random random = new Random();
		String [] temps = value.split(",");
		String variable1 = temps[0];
		String variable2 = temps[1];
		String variable3 = temps[2];
		String labase = temps[3];
		LogicInfo logicInfo = new LogicInfo();
		logicInfo.setVariable1(variable1);
		logicInfo.setVariable2(variable2);
		logicInfo.setVariable3(variable3);
		logicInfo.setLabase(labase);
		logicInfo.setGroupbyfield("logic=="+random.nextInt(10));
		return logicInfo;
	}
}
