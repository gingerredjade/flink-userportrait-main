package com.jhy.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * 读properties配置文件工具类
 *
 * Created by JHy on 2019/04/26.
 */
public class ReadProperties {
    public final static Config config = ConfigFactory.load("jhy.properties");
    public static String getKey(String key){
        return config.getString(key).trim();
    }

    public static String getKey(String key,String filename){
        Config config =  ConfigFactory.load(filename);
        return config.getString(key).trim();
    }
}
