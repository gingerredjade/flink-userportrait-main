package com.jhy.entity;

import java.util.List;
import java.util.Map;

/**
 * 实体类
 * Created by JHy on 2019/04/25
 */
public class KeyWordEntity {

    private String userid;                  // 用户编号
    private Map<String,Long> datamap;       //
    private Map<String,Double> tfmap;       //
    private Long totaldocumet;              //
    private List<String> finalkeyword;      //
    private List<String> originalwords;     //

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
