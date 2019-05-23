package com.jhy.tfIdf;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

/**
 * IK分词工具测试
 * Created by JHy on 2019/5/20.
 */
public class IkTest {
    public static void main(String[] args) {
        String test = "我喜欢晴天 阳光真的很棒 我喜欢温暖的朝阳";
        // 创建分词对象
        Analyzer anal = new IKAnalyzer(true);
        StringReader reader = new StringReader(test);
        // 分词
        TokenStream ts= null;
        try {
            ts = anal.tokenStream("", reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取每个单词信息(保存相应词汇)
        CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);
        // 遍历分词数据
        try {
            while(ts.incrementToken()){
                String result = term.toString();
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader.close();
    }
}
