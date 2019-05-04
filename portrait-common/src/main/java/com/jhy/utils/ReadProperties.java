package com.jhy.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Created by li on 2019/1/6.
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
