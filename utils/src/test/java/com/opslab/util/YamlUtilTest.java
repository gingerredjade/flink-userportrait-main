package com.opslab.util;

import org.junit.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.*;
import java.util.*;


public class YamlUtilTest {

	YamlUtil yamlUtil = new YamlUtil();

	@Test
	public void getYmlByFileNamePerfect() throws FileNotFoundException {
		// 示例yaml文件内容参见resource/configserver-application-0.1.0.0 - 副本.yaml
		// 示例yaml文件内容参见resource/mswss-application-0.2.0.82-bigdata-146.yaml
		//List<LinkedHashMap<String, String>> mapList = yamlUtil.getYmlByFileNamePerfect("E:/mswss-application-0.2.0.82-bigdata-146.yaml");
		List<LinkedHashMap<String, String>> mapList = yamlUtil.getYmlByFileNamePerfect("E:/1.yaml");

		for (LinkedHashMap<String, String> map:mapList) {
			Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()) {
				Map.Entry<String, String> next = iterator.next();
				System.out.println(next.getKey()+"================="+next.getValue());
			}

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}

	@Test
	public void getYmlByFileName() throws FileNotFoundException {
		// 示例yaml文件内容参见resource/configserver-application-0.1.0.0.yaml
		LinkedHashMap<String, String> map = yamlUtil.getYmlByFileName("E:/configserver-application-0.1.0.0.yaml");

		// 遍历方式一
		Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, String> next = iterator.next();
			System.out.println(next.getKey()+"================="+next.getValue());
		}

		// 遍历方式二
		/*for (Map.Entry<String, String> entry:map.entrySet()) {
			System.out.println(entry.getKey()+"================="+entry.getValue());
		}*/
	}

	@Test
	public void getValue() throws FileNotFoundException {
		String filename = "E:/configserver-application-0.1.0.0.yaml";
		String key = "spec.template.spec.containers";
		String value = yamlUtil.getValue(filename, key);
		System.out.println(value);
	}

	@Test
	public void dump() {
		Address adr = new Address();
		adr.setCity("Royal Oak");
		adr.setLines("458 Walkman");
		adr.setPostal("48046");
		adr.setState("MI");

		Yaml yaml = new Yaml();
		StringWriter stringWriter = new StringWriter();
		yaml.dump(adr, stringWriter);
		System.out.println(stringWriter.toString());
	}

	@Test
	public void dumpAll() throws IOException {
		// 1-- 组织数据
		// 1--1 对象列表类型数据
		Address adr1 = new Address();
		adr1.setCity("Royal Oak");
		adr1.setLines("458 Walkman");
		adr1.setPostal("48046");
		adr1.setState("MI");

		Address adr2 = new Address();
		adr2.setCity("Royal Oak");
		adr2.setLines("458 Walkman");
		adr2.setPostal("48046");
		adr2.setState("MI");

		List<Address> list = new ArrayList<>();
		list.add(adr1);
		list.add(adr2);

		// 1--2 Map对象类型数据
		Map<String, Object> data = new HashMap<>();
		data.put("name", "Silenthand Olleander".trim());
		data.put("race", "Human".trim());
		data.put("chinese", "哈哈".trim());

		// 2-- prepare dump操作
		// 2--1 Representer用于配置yaml文件中对应tag响应解析成的对象
		Representer representer = new Representer();
		// 这里的Tag.MAP表示使用Address对map的类型进行解析
		representer.addClassTag(Address.class, Tag.MAP);
		representer.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		representer.setDefaultScalarStyle(DumperOptions.ScalarStyle.FOLDED);

		// 2--2 DumperOptions 在dump时控制输出的宽度、对齐、类类型等相关信息
		// normal
		DumperOptions optionsNormal = new DumperOptions();
		optionsNormal.setDefaultFlowStyle(DumperOptions.FlowStyle.FLOW);

		// pretty
		DumperOptions optionsPretty = new DumperOptions();
		optionsPretty.setIndent(2);
		optionsPretty.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		optionsPretty.setPrettyFlow(true);
		optionsPretty.setDefaultScalarStyle(DumperOptions.ScalarStyle.FOLDED);
		optionsPretty.setAllowUnicode(true);
		optionsPretty.setSplitLines(false);

		// 3-- dump操作
		Yaml yaml = new Yaml(representer, optionsPretty);
		FileWriter fw = new FileWriter("E:\\1.yaml");

		yaml.dumpAll(list.iterator(), fw);
		yaml.dump(data, fw);

		StringWriter stringWriter = new StringWriter();
		yaml.dumpAll(list.iterator(), stringWriter);
		System.out.println(stringWriter.toString());
	}


	/**
	 * 测试用实体类
	 */
	class Address {
		private String city;
		private String lines;
		private String postal;
		private String state;

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getLines() {
			return lines;
		}

		public void setLines(String lines) {
			this.lines = lines;
		}

		public String getPostal() {
			return postal;
		}

		public void setPostal(String postal) {
			this.postal = postal;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}
	}




}
