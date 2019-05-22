package com.jhy.control;

import com.alibaba.fastjson.JSONObject;
import com.jhy.entity.ViewResultAnaly;
import com.jhy.form.AnalyForm;
import com.jhy.service.HBaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 前端查询HBase里的各项数据
 *
 * Created by JHy on 2019/5/22.
 */
@RestController
@RequestMapping("hbaseData")
@CrossOrigin
public class HBaseDataViewControl {

    @Autowired
	HBaseDataService hbaseDataService;

    @RequestMapping(value = "resultinfoView",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String resultinfoView(@RequestBody AnalyForm analyForm){
        String type = analyForm.getType();
        String userid = analyForm.getUserid();
        String result = "";
        List<ViewResultAnaly> resultlist = new ArrayList<ViewResultAnaly>();
        if("-1".equals(type)){
            ViewResultAnaly viewResultAnaly = new ViewResultAnaly();
            result = hbaseDataService.baiJiaZhiShuInfo(userid);
            viewResultAnaly.setTypename("败家指数");
            viewResultAnaly.setLablevalue(result);
            resultlist.add(viewResultAnaly);
            viewResultAnaly = new ViewResultAnaly();
            result = hbaseDataService.brandLike(userid);
            viewResultAnaly.setTypename("品牌偏好");
            viewResultAnaly.setLablevalue(result);
            resultlist.add(viewResultAnaly);
            viewResultAnaly = new ViewResultAnaly();
            result = hbaseDataService.carrierinfo(userid);
            viewResultAnaly.setTypename("运营商");
            viewResultAnaly.setLablevalue(result);
            resultlist.add(viewResultAnaly);
            viewResultAnaly = new ViewResultAnaly();
            result = hbaseDataService.chaomanandwomen(userid);
            viewResultAnaly.setTypename("潮男潮女");
            viewResultAnaly.setLablevalue(result);
            resultlist.add(viewResultAnaly);
            viewResultAnaly = new ViewResultAnaly();
            result = hbaseDataService.consumptionlevel(userid);
            viewResultAnaly.setTypename("消费水平");
            viewResultAnaly.setLablevalue(result);
            resultlist.add(viewResultAnaly);
            viewResultAnaly = new ViewResultAnaly();
            result = hbaseDataService.emailinfo(userid);
            viewResultAnaly.setTypename("邮件运营商");
            viewResultAnaly.setLablevalue(result);
            resultlist.add(viewResultAnaly);
            viewResultAnaly = new ViewResultAnaly();
            result = hbaseDataService.yearkeyword(userid);
            viewResultAnaly.setTypename("年度关键词");
            viewResultAnaly.setLablevalue(result);
            resultlist.add(viewResultAnaly);
            viewResultAnaly = new ViewResultAnaly();
            result = hbaseDataService.sex(userid);
            viewResultAnaly.setTypename("性别");
            viewResultAnaly.setLablevalue(result);
            resultlist.add(viewResultAnaly);
            viewResultAnaly = new ViewResultAnaly();
            result = hbaseDataService.usergroupinfo(userid);
            viewResultAnaly.setTypename("用户群体特征");
            viewResultAnaly.setLablevalue(result);
            resultlist.add(viewResultAnaly);
            viewResultAnaly = new ViewResultAnaly();
            result = hbaseDataService.usetypeinfo(userid);
            viewResultAnaly.setTypename("终端偏好");
            viewResultAnaly.setLablevalue(result);
            resultlist.add(viewResultAnaly);
            viewResultAnaly = new ViewResultAnaly();
            result = hbaseDataService.ageinfo(userid);
            viewResultAnaly.setTypename("年龄");
            viewResultAnaly.setLablevalue(result);
            resultlist.add(viewResultAnaly);
            viewResultAnaly = new ViewResultAnaly();
            viewResultAnaly.setList(resultlist);
            String resultjson = JSONObject.toJSONString(viewResultAnaly);
            return resultjson;
        }
        if("baiJiaZhiShuInfo".equals(type)){
            result = hbaseDataService.baiJiaZhiShuInfo(userid);
        }else if ("brandLike".equals(type)){
            result = hbaseDataService.brandLike(userid);
        }else if ("carrierinfo".equals(type)){
            result = hbaseDataService.carrierinfo(userid);
        }else if ("chaomanandwomen".equals(type)){
            result = hbaseDataService.chaomanandwomen(userid);
        }else if ("consumptionlevel".equals(type)){
            result = hbaseDataService.consumptionlevel(userid);
        }else if ("emailinfo".equals(type)){
            result = hbaseDataService.emailinfo(userid);
        }else if ("yearkeyword".equals(type)){
            result = hbaseDataService.yearkeyword(userid);
        }else if ("monthkeyword".equals(type)){
            result = hbaseDataService.monthkeyword(userid);
        }else if ("quarterkeyword".equals(type)){
            result = hbaseDataService.quarterkeyword(userid);
        }else if ("sex".equals(type)){
            result = hbaseDataService.sex(userid);
        }else if ("usergroupinfo".equals(type)){
            result = hbaseDataService.usergroupinfo(userid);
        }else if ("usetypeinfo".equals(type)){
            result = hbaseDataService.usetypeinfo(userid);
        }else if ("ageinfo".equals(type)){
            result = hbaseDataService.ageinfo(userid);
        }
        ViewResultAnaly viewResultAnaly = new ViewResultAnaly();
        viewResultAnaly.setResult(result);
        result = JSONObject.toJSONString(viewResultAnaly);
        return result;
    }
}
