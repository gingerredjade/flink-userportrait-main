package com.jhy.service;

import com.jhy.entity.AnalyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by JHy on 2019/5/16.
 */
@FeignClient(value = "jhySearchInfo")
public interface MongoDataService {

    @RequestMapping(value = "yearBase/searchYearBase",method = RequestMethod.POST)
    public List<AnalyResult> searchYearBase();

    @RequestMapping(value = "yearBase/searchUseType",method = RequestMethod.POST)
    public List<AnalyResult> searchUseType();

    @RequestMapping(value = "yearBase/searchEmail",method = RequestMethod.POST)
    public List<AnalyResult> searchEmail();

    @RequestMapping(value = "yearBase/searchConsumptionlevel",method = RequestMethod.POST)
    public List<AnalyResult> searchConsumptionlevel();

    @RequestMapping(value = "yearBase/searchChaoManAndWomen",method = RequestMethod.POST)
    public List<AnalyResult> searchChaoManAndWomen();

    @RequestMapping(value = "yearBase/searchCarrier",method = RequestMethod.POST)
    public List<AnalyResult> searchCarrier();

    @RequestMapping(value = "yearBase/searchBrandlike",method = RequestMethod.POST)
    public List<AnalyResult> searchBrandlike();
}
