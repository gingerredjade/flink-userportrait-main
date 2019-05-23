package com.jhy.entity;

import java.util.List;
import java.util.Map;

/**
 * 关键词实体类
 * 		[会摘取标题、摘取商品详情一部分内容]
 *
 * Created by JHy on 2019/04/25
 */
public class KeyWordEntity {

    private String userid;                  // 用户编号，一个用户id对应它所有商品的关键词
    private Map<String,Long> datamap;       // 某词出现的次数（关键词j在文档i中出现的次数）
    private Map<String,Double> tfmap;       // 某词出现的频率即TF（关键词j在文档i中的出现频率）
    private Long totaldocumet;              // 总文档数
    private List<String> finalkeyword;      // 最终的关键词集
    private List<String> originalwords;     // 原始的词集

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Map<String, Long> getDatamap() {
        return datamap;
    }

    public void setDatamap(Map<String, Long> datamap) {
        this.datamap = datamap;
    }

    public Map<String, Double> getTfmap() {
        return tfmap;
    }

    public void setTfmap(Map<String, Double> tfmap) {
        this.tfmap = tfmap;
    }

    public Long getTotaldocumet() {
        return totaldocumet;
    }

    public void setTotaldocumet(Long totaldocumet) {
        this.totaldocumet = totaldocumet;
    }

    public List<String> getFinalkeyword() {
        return finalkeyword;
    }

    public void setFinalkeyword(List<String> finalkeyword) {
        this.finalkeyword = finalkeyword;
    }

    public List<String> getOriginalwords() {
        return originalwords;
    }

    public void setOriginalwords(List<String> originalwords) {
        this.originalwords = originalwords;
    }
}
