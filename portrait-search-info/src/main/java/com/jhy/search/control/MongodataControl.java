package com.jhy.search.control;

import com.jhy.entity.AnalyResult;
import com.jhy.search.service.MongoDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JHy on 2019/5/19.
 */

/**
 * 年代：yearbasestatics
 终端偏好：usetypestatics
 邮件运营商：emailstatics
 消费水平：consumptionlevelstatics
 潮男潮女：chaoManAndWomenstatics
 手机运营商：carrierstatics
 品牌偏好：brandlikestatics
 */
@RestController
@RequestMapping("yearBase")
public class MongodataControl {

    @Autowired
    private MongoDataServiceImpl mongoDataServiceImpl;

    @RequestMapping(value = "searchYearBase",method = RequestMethod.POST)
    public List<AnalyResult> searchYearBase(){
        List<AnalyResult> list = new ArrayList<AnalyResult>();
        AnalyResult analyResult = new AnalyResult();
        // 40年代，50年代，60年代，70年代，80年代，90年代，00年代 10后
        analyResult.setCount(50l);
        analyResult.setInfo("40年代");
        list.add(analyResult);
        analyResult = new AnalyResult();
        analyResult.setCount(60l);
        analyResult.setInfo("50年代");
        list.add(analyResult);
        analyResult = new AnalyResult();
        analyResult.setCount(100l);
        analyResult.setInfo("60年代");
        list.add(analyResult);
        analyResult = new AnalyResult();
        analyResult.setCount(90l);
        analyResult.setInfo("70年代");
        list.add(analyResult);
        analyResult = new AnalyResult();
        analyResult.setCount(500l);
        analyResult.setInfo("80年代");
        list.add(analyResult);
        analyResult = new AnalyResult();
        analyResult.setCount(600l);
        analyResult.setInfo("90年代");
        list.add(analyResult);
        analyResult = new AnalyResult();
        analyResult.setCount(300l);
        analyResult.setInfo("00年代");
        list.add(analyResult);
        analyResult = new AnalyResult();
        analyResult.setCount(70l);
        analyResult.setInfo("10后");

        list.add(analyResult);

        return list;
//        return mongoDataServiceImpl.listMongoInfoby("yearbasestatics");
    }

    @RequestMapping(value = "searchUseType",method = RequestMethod.POST)
    public List<AnalyResult> searchUseType(){
        List<AnalyResult> list = new ArrayList<AnalyResult>();
        AnalyResult analyResult = new AnalyResult();
        //pc端，小程序端，移动端
        analyResult.setCount(50l);
        analyResult.setInfo("pc端");
        list.add(analyResult);

        analyResult = new AnalyResult();
        analyResult.setCount(60l);
        analyResult.setInfo("小程序端");
        list.add(analyResult);

        analyResult = new AnalyResult();
        analyResult.setCount(40l);
        analyResult.setInfo("移动端");
        list.add(analyResult);

        return list;
//        return mongoDataServiceImpl.listMongoInfoby("usetypestatics");
    }

    @RequestMapping(value = "searchEmail",method = RequestMethod.POST)
    public List<AnalyResult> searchEmail(){
        List<AnalyResult> list = new ArrayList<AnalyResult>();
        AnalyResult analyResult = new AnalyResult();
        // qq邮箱，139邮箱，网易邮箱,阿里邮箱
        analyResult.setCount(150l);
        analyResult.setInfo("qq邮箱");
        list.add(analyResult);

        analyResult = new AnalyResult();
        analyResult.setCount(60l);
        analyResult.setInfo("139邮箱");
        list.add(analyResult);

        analyResult = new AnalyResult();
        analyResult.setCount(240l);
        analyResult.setInfo("网易邮箱");
        list.add(analyResult);

        analyResult = new AnalyResult();
        analyResult.setCount(540l);
        analyResult.setInfo("阿里邮箱");
        list.add(analyResult);

        return list;

//        return mongoDataServiceImpl.listMongoInfoby("emailstatics");
    }

    @RequestMapping(value = "searchConsumptionlevel",method = RequestMethod.POST)
    public List<AnalyResult> searchConsumptionlevel(){
        // 高消费 中等消费  低消费
        List<AnalyResult> list = new ArrayList<AnalyResult>();
        AnalyResult analyResult = new AnalyResult();
        // qq邮箱，139邮箱，网易邮箱,阿里邮箱
        analyResult.setCount(50l);
        analyResult.setInfo("高消费");
        list.add(analyResult);

        analyResult = new AnalyResult();
        analyResult.setCount(560l);
        analyResult.setInfo("中等消费");
        list.add(analyResult);

        analyResult = new AnalyResult();
        analyResult.setCount(760l);
        analyResult.setInfo("低消费");
        list.add(analyResult);

        return list;

//        return mongoDataServiceImpl.listMongoInfoby("consumptionlevelstatics");
    }

    @RequestMapping(value = "searchChaoManAndWomen",method = RequestMethod.POST)
    public List<AnalyResult> searchChaoManAndWomen(){
        // 潮男 潮女
        List<AnalyResult> list = new ArrayList<AnalyResult>();
        AnalyResult analyResult = new AnalyResult();

        analyResult.setCount(350l);
        analyResult.setInfo("潮男");
        list.add(analyResult);

        analyResult = new AnalyResult();
        analyResult.setCount(560l);
        analyResult.setInfo("潮女");
        list.add(analyResult);

        return list;

//        return mongoDataServiceImpl.listMongoInfoby("chaoManAndWomenstatics");
    }

    @RequestMapping(value = "searchCarrier",method = RequestMethod.POST)
    public List<AnalyResult> searchCarrier(){
        // 联通 移动 电信 其他
        List<AnalyResult> list = new ArrayList<AnalyResult>();
        AnalyResult analyResult = new AnalyResult();

        analyResult.setCount(1350l);
        analyResult.setInfo("联通");
        list.add(analyResult);

        analyResult = new AnalyResult();
        analyResult.setCount(1560l);
        analyResult.setInfo("移动");
        list.add(analyResult);

        analyResult = new AnalyResult();
        analyResult.setCount(560l);
        analyResult.setInfo("电信");
        list.add(analyResult);

        analyResult = new AnalyResult();
        analyResult.setCount(4560l);
        analyResult.setInfo("其他");
        list.add(analyResult);

        return list;
//        return mongoDataServiceImpl.listMongoInfoby("carrierstatics");
    }

    @RequestMapping(value = "searchBrandlike",method = RequestMethod.POST)
    public List<AnalyResult> searchBrandlike(){
        // 李宁 爱迪达斯 森马 海尔
        List<AnalyResult> list = new ArrayList<AnalyResult>();
        AnalyResult analyResult = new AnalyResult();

        analyResult.setCount(1350l);
        analyResult.setInfo("李宁");
        list.add(analyResult);

        analyResult = new AnalyResult();
        analyResult.setCount(1560l);
        analyResult.setInfo("爱迪达斯");
        list.add(analyResult);

        analyResult = new AnalyResult();
        analyResult.setCount(560l);
        analyResult.setInfo("森马");
        list.add(analyResult);

        analyResult = new AnalyResult();
        analyResult.setCount(4560l);
        analyResult.setInfo("海尔");
        list.add(analyResult);

        return list;

//        return mongoDataServiceImpl.listMongoInfoby("brandlikestatics");
    }

}
