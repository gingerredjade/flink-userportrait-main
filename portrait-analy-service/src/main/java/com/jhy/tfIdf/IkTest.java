package com.jhy.tfIdf;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by li on 2019/1/20.
 */
public class IkTest {
    public static void main(String[] args) {
        String test = "我喜欢大晴天 阳光真的很棒";
        // 创建分词对象
        Analyzer anal=new IKAnalyzer(true);
        StringReader reader=new StringReader(test);
        // 分词
        TokenStream ts= null;
        try {
            ts = anal.tokenStream("", reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
