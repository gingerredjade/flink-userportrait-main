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

    private static Analyzer anal = new IKAnalyzer(true);
    public static List<String> getIkWord(String word){
        List<String> resultlist = new ArrayList<String>();
        StringReader reader=new StringReader(word);

        //分词
        TokenStream ts= null;
        try {
            ts = anal.tokenStream("", reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);

        //遍历分词数据
        try {
            while(ts.incrementToken()){
                String result = term.toString();
                resultlist.add(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader.close();
        return resultlist;
    }

}
