package com.opslab.util;


import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.*;

/**
 * 提供一些YAML文件常用操作方法
 * 		使用LinkedHashMap以保证输出顺序与写入顺序一致，速度还是HashMap快
 *
 * Created by JHy on 2019/06/26
 */
public class YamlUtil {

	private static String YAMLFILE = "bootstrap.yaml";

	private static LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
	private static List<LinkedHashMap<String, String>> resultArray = new ArrayList<>();

	/**
	 * 根据Key从系统属性文件中获取相应的值
	 * @param key
	 * @return
	 */
	public final static String key(String key) {
		return System.getProperty(key);
	}

	/**
	 * 根据文件名获取yml的文件内容
	 * 		支持一个yaml文件包含多个yaml配置片段。
	 *
	 * @param filename
	 * @return	返回所有的属性
	 */
	public static List<LinkedHashMap<String, String>> getYmlByFileNamePerfect(String filename)
		throws FileNotFoundException {
		resultArray = new ArrayList<>();
		if (filename == null) {
			filename = YAMLFILE;
		}

		// 1-- 获取文件流
		File file = new File(filename);
		FileInputStream fis = new FileInputStream(file);

		// 2-- 实例化yaml解析器
		Yaml yaml = new Yaml();

		// 3-- 加载文件数据并解析
		Iterable<Object> ret = yaml.loadAll(fis);
		// 3--1 遍历Obejct迭代对象
		for (Object o:ret) {
			//System.out.println(o);
			result = new LinkedHashMap<String, String>();

			// 3--2 解析对象内容
			Map<String, Object> param = (Map<String, Object>) o;
			for (Map.Entry<String, Object> entry:param.entrySet()) {
				String key = entry.getKey();
				Object val = entry.getValue();
				if (val instanceof Map) {
					forEachYaml(key, (Map<String, Object>) val);
				} else {
					result.put(key, val.toString());
				}
			}
			resultArray.add(result);
		}

		// 4-- 响应解析结果
		return resultArray;
	}

	/**
	 * 根据文件名获取yml的文件内容
	 * 		要求yaml文件中只有一个yaml文件内容配置片段。不支持使用‘---’代表文档的开始，加入多个yaml配置片段的文件的yaml文件。
	 *
	 * @param filename
	 * @return	返回所有的属性
	 */
	public static LinkedHashMap<String, String> getYmlByFileName(String filename)
		throws FileNotFoundException {
		result = new LinkedHashMap<String, String>();
		if (filename == null) {
			filename = YAMLFILE;
		}

		// 1-- 获取文件流
		File file = new File(filename);
		FileInputStream fis = new FileInputStream(file);

		// 2-- 创建Yaml对象，初始化yaml解析器，所有的解析操作都是从这个对象开始
		Yaml props = new Yaml();

		// 3-- 加载文件数据并解析
		// 3--1 载入文件数据
		Map<String, Object> param = props.loadAs(fis, Map.class);

		/*Object obj = props.loadAs(fis, Map.class);
		Map<String, Object> param = (Map<String, Object>) obj;*/

		// 3--2 遍历载入的文件数据
		for (Map.Entry<String, Object> entry:param.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();
			if (val instanceof Map) {
				forEachYaml(key, (Map<String, Object>) val);
			} else {
				result.put(key, val.toString());
			}
		}

		// 4-- 响应解析结果
		return result;
	}

	/**
	 * 遍历yml文件，获取map集合
	 * @param key_str
	 * @param obj
	 * @return
	 */
	public static Map<String, String> forEachYaml(String key_str, Map<String, Object> obj) {
		for (Map.Entry<String, Object> entry:obj.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();

			String str_new = "";
			if (key_str != null && key_str.length() != 0) {
				str_new = key_str + "." + key;
			} else {
				str_new = key;
			}

			if (val instanceof Map) {
				forEachYaml(str_new, (Map<String, Object>) val);
			} else {
				result.put(str_new, val.toString());
			}
		}

		return result;
	}

	/**
	 * 根据yml文件名、key获取对应值
	 * 		如果yaml文件中出现下面所示的'-'形式的数组形式，通过'.'形式传递key是获取不到的,应自行解析。
	 *
	 * 		spec:
	 *       containers:               								# Pod内容器的定义部分
	 *       - name: mswss             								# 容器的名称
	 *         image: reg.gisnci.com/mirrors-gis/cfgcenter:latest   # 容器对应的Docker Image
	 *         ports:
	 *         - containerPort: 8761   								# 容器应用监听的端口号
	 *           protocol: TCP
	 *
	 *
	 * @param filename	yaml配置文件名称
	 * @param key		需要读取的属性
	 * @return
	 */
	public static String getValue(String filename, String key) throws FileNotFoundException {
		Map<String, String> ymlMap = getYmlByFileName(filename);
		if (ymlMap.isEmpty() || ymlMap == null) {
			return null;
		}
		return ymlMap.get(key);
	}

	/**
	 * 输出一个对象到StringWriter
	 * @param obj
	 * @return
	 */
	public static StringWriter dump(Object obj) {
		Yaml yaml = new Yaml();
		StringWriter stringWriter = new StringWriter();
		yaml.dump(obj, stringWriter);
		return stringWriter;
	}

	/**
	 * 输出一组对象到StringWriter
	 * @param list
	 * @return
	 */
	public static StringWriter dumpAll(List list) {
		Yaml yaml = new Yaml();
		StringWriter stringWriter = new StringWriter();
		yaml.dumpAll(list.iterator(), stringWriter);
		return stringWriter;
	}




// TODO..  写入的yaml属性文件 属性名称 属性值     使用期生成yaml文件。


	/**
	 * ###知识点记录###
	 *
	 * SpringBoot使用的SnakeYaml。
	 * SnakeYaml是针对Java语言的YAML解析器。
	 * 是一个完整的YAML1.1规范的Processor，支持UTF-8/UTF-16，支持Java对象的序列化/反序列化，支持所有YAML定义的类型（map,omap,set,常量）。
	 * 支持合并'<<'和'---'操作
	 * 通过读入的内容是否包含BOM头来判断输入流的编码格式。如果不包含BOM头，默认为UTF-8编码。
	 *
	 *
	 */



}

