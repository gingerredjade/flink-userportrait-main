package com.jhy.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * IKAnalyzer工具类
 * Created by JHy on 2019/04/25
 */
public class IkUtils {

	// 创建IK分词对象，true表示使用智能分词
    private static Analyzer anal = new IKAnalyzer(true);

    public static List<String> getIkWord(String word){
    	// 分词结果存储集合
        List<String> resultlist = new ArrayList<String>();

        // 1-- 读取数据为字符流
        StringReader reader = new StringReader(word);

        // 2-- 分词
        TokenStream ts = null;
        try {
            ts = anal.tokenStream("", reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取每个单词信息(保存相应词汇)
        CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);

        // 3-- 遍历分词数据
        try {
            while(ts.incrementToken()){
                String result = term.toString();
                resultlist.add(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader.close();

        // 4-- 返回分词结果
        return resultlist;
    }

}
