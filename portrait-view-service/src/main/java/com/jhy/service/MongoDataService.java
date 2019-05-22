package com.jhy.service;

import com.jhy.entity.AnalyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 *
 * 	FeignClient的值要配置其调用服务端的服务名称即spring.application.name。
 *
 * Created by JHy on 2019/5/16.
 */
@FeignClient(value = "portrait-search-info")
public interface MongoDataService {

    @RequestMapping(value = "mongoData/searchYearBase",method = RequestMethod.POST)
    public List<AnalyResult> searchYearBase();

    @RequestMapping(value = "mongoData/searchUseType",method = RequestMethod.POST)
    public List<AnalyResult> searchUseType();

    @RequestMapping(value = "mongoData/searchEmail",method = RequestMethod.POST)
    public List<AnalyResult> searchEmail();

    @RequestMapping(value = "mongoData/searchConsumptionlevel",method = RequestMethod.POST)
    public List<AnalyResult> searchConsumptionlevel();

    @RequestMapping(value = "mongoData/searchChaoManAndWomen",method = RequestMethod.POST)
    public List<AnalyResult> searchChaoManAndWomen();

    @RequestMapping(value = "mongoData/searchCarrier",method = RequestMethod.POST)
    public List<AnalyResult> searchCarrier();

    @RequestMapping(value = "mongoData/searchBrandlike",method = RequestMethod.POST)
    public List<AnalyResult> searchBrandlike();
}
