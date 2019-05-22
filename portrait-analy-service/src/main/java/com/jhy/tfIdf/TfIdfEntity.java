package com.jhy.tfIdf;

import java.util.List;
import java.util.Map;

/**
 * Created by li on 2019/1/20.
 */
public class TfIdfEntity {
    private String documentid;			// 文档id
    private Map<String,Long> datamap;	// 某词出现的次数（关键词j在文档i中出现的次数）
    private Map<String,Double> tfmap;	// 某词出现的频率即TF（关键词j在文档i中的出现频率）
    private Long totaldocument;			// 总文档数
    private List<String> finalword;		// 最终的word集合

    public List<String> getFinalword() {
        return finalword;
    }

    public void setFinalword(List<String> finalword) {
        this.finalword = finalword;
    }

    public Long getTotaldocument() {
        return totaldocument;
    }

    public void setTotaldocument(Long totaldocument) {
        this.totaldocument = totaldocument;
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
