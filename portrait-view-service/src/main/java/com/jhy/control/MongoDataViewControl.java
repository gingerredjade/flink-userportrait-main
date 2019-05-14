package com.jhy.control;

import com.alibaba.fastjson.JSONObject;
import com.jhy.entity.AnalyResult;
import com.jhy.entity.ViewResultAnaly;
import com.jhy.form.AnalyForm;
import com.jhy.service.MongoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by li on 2019/1/19.
 */
@RestController
@RequestMapping("mongoData")
@CrossOrigin
public class MongoDataViewControl {

    @Autowired
	MongoDataService mongoDataService;

    @RequestMapping(value = "resultinfoView",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String resultinfoView(@RequestBody AnalyForm analyForm){
        String type = analyForm.getType();
        List<AnalyResult> list = new ArrayList<AnalyResult>();
        if("yearBase".equals(type)){
            list = mongoDataService.searchYearBase();
        }else if ("useType".equals(type)){
            list = mongoDataService.searchUseType();
        }else if ("email".equals(type)){
            list = mongoDataService.searchEmail();
        }else if ("consumptionlevel".equals(type)){
            list = mongoDataService.searchConsumptionlevel();
        }else if ("carrier".equals(type)){
            list = mongoDataService.searchCarrier();
        }else if ("chaoManAndWomen".equals(type)){
            list = mongoDataService.searchChaoManAndWomen();
        }else if ("brandlike".equals(type)){
            list = mongoDataService.searchBrandlike();
        }
        ViewResultAnaly viewResultAnaly = new ViewResultAnaly();
        List<String> infolist = new ArrayList<String>();		// 分组list，x轴的值
        List<Long> countlist =new ArrayList<Long>();		// 数量
        for(AnalyResult analyResult:list){
            infolist.add(analyResult.getInfo());
            countlist.add(analyResult.getCount());
        }
        viewResultAnaly.setInfolist(infolist);
        viewResultAnaly.setCountlist(countlist);
        String result = JSONObject.toJSONString(viewResultAnaly);
        return result;
    }
}
