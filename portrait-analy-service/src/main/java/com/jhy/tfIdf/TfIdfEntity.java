package com.jhy.tfIdf;

import java.util.List;
import java.util.Map;

/**
 * Created by li on 2019/1/20.
 */
public class TfIdfEntity {
    private String documentid;
    private Map<String,Long> datamap;
    private Map<String,Double> tfmap;
    private Long totaldocumet;
    private List<String> finalword;

    public List<String> getFinalword() {
        return finalword;
    }

    public void setFinalword(List<String> finalword) {
        this.finalword = finalword;
    }

    public Long getTotaldocumet() {
        return totaldocumet;
    }

    public void setTotaldocumet(Long totaldocumet) {
        this.totaldocumet = totaldocumet;
    }

    public Map<String, Double> getTfmap() {
        return tfmap;
    }

    public void setTfmap(Map<String, Double> tfmap) {
        this.tfmap = tfmap;
    }

    public String getDocumentid() {
        return documentid;
    }

    public void setDocumentid(String documentid) {
        this.documentid = documentid;
    }

    public Map<String, Long> getDatamap() {
        return datamap;
    }

    public void setDatamap(Map<String, Long> datamap) {
        this.datamap = datamap;
    }
}
