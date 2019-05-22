package com.jhy.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *
 * 		FeignClient的值要配置其调用服务端的服务名称即spring.application.name。
 *
 * Created by JHy on 2019/5/16.
 */
@FeignClient(value = "portrait-search-info")
public interface HBaseDataService {

    @RequestMapping(value = "hbaseData/baiJiaZhiShuInfo",method = RequestMethod.POST)
    public String baiJiaZhiShuInfo(String userid);


    @RequestMapping(value = "hbaseData/brandLike",method = RequestMethod.POST)
    public String brandLike(String userid);

    @RequestMapping(value = "hbaseData/carrierinfo",method = RequestMethod.POST)
    public String carrierinfo(String userid);

    @RequestMapping(value = "hbaseData/chaomanandwomen",method = RequestMethod.POST)
    public String chaomanandwomen(String userid);

    @RequestMapping(value = "hbaseData/consumptionlevel",method = RequestMethod.POST)
    public String consumptionlevel(String userid);

    @RequestMapping(value = "hbaseData/emailinfo",method = RequestMethod.POST)
    public String emailinfo(String userid);

    @RequestMapping(value = "hbaseData/yearkeyword",method = RequestMethod.POST)
    public String yearkeyword(String userid);

    @RequestMapping(value = "hbaseData/monthkeyword",method = RequestMethod.POST)
    public String monthkeyword(String userid);

    @RequestMapping(value = "hbaseData/quarterkeyword",method = RequestMethod.POST)
    public String quarterkeyword(String userid);

    @RequestMapping(value = "hbaseData/sex",method = RequestMethod.POST)
    public String sex(String userid);


    @RequestMapping(value = "hbaseData/usergroupinfo",method = RequestMethod.POST)
    public String usergroupinfo(String userid);

    @RequestMapping(value = "hbaseData/usetypeinfo",method = RequestMethod.POST)
    public String usetypeinfo(String userid);

    @RequestMapping(value = "hbaseData/ageinfo",method = RequestMethod.POST)
    public String ageinfo(String userid);

}
