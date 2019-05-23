package com.jhy.search.control;

import com.jhy.search.service.HBaseServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * HBase Controller 接口查询服务REST
 * Created by JHy on 2019/5/20.
 */

/**
 */
@RestController
@RequestMapping("hbaseData")
public class HBaseDataControl {

	// 败家指数
    @RequestMapping(value = "baiJiaZhiShuInfo",method = RequestMethod.POST)
    public String baiJiaZhiShuInfo(String userid){
        String result = "";

//        String tablename = "userflaginfo";
//        String rowkey = userid;
//        String famliyname = "baseinfo";
//        String colum = "baijiasoce";
//
//        try {
//            result = HBaseServiceImpl.getdata(tablename,rowkey,famliyname,colum);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        result = "属于中等败家(56)";
        return result;
    }

	// 品牌偏好
    @RequestMapping(value = "brandLike",method = RequestMethod.POST)
    public String brandLike(String userid){
        String result = "";
//        String tablename = "userflaginfo";
//        String rowkey = userid+"";
//        String famliyname = "userbehavior";
//        String colum = "brandlist";//运营
//        String result = "";
//        try {
//            result = HBaseServiceImpl.getdata(tablename,rowkey,famliyname,colum);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        result = "李宁,乔丹";
        return result;
    }

    // 手机运营商
    @RequestMapping(value = "carrierinfo",method = RequestMethod.POST)
    public String carrierinfo(String userid){
        String result = "";
//        String tablename = "userflaginfo";
//        String rowkey = userid;
//        String famliyname = "baseinfo";
//        String colum = "carrierinfo";//运营商
//        String result = "";
//        try {
//            result = HBaseServiceImpl.getdata(tablename,rowkey,famliyname,colum);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        result = "移动用户";
        return result;
    }

    // 潮男潮女
    @RequestMapping(value = "chaomanandwomen",method = RequestMethod.POST)
    public String chaomanandwomen(String userid){
        String result = "";
//        String tablename = "userflaginfo";
//        String rowkey = userid;
//        String famliyname = "userbehavior";
//        String colum = "chaomanandwomen";
//        String result = "";
//        try {
//            result = HBaseServiceImpl.getdata(tablename,rowkey,famliyname,colum);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        result = "赶在时尚潮流前端的女性";
        return result;
    }

    // 消费水平
    @RequestMapping(value = "consumptionlevel",method = RequestMethod.POST)
    public String consumptionlevel(String userid){
        String result = "";
//        String tablename = "userflaginfo";
//        String rowkey = userid+"";
//        String famliyname = "consumerinfo";
//        String colum = "consumptionlevel";
//        String result = "";
//        try {
//            result = HBaseServiceImpl.getdata(tablename,rowkey,famliyname,colum);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        result = "中等消费者";
        return result;
    }

    // 邮件运营商
    @RequestMapping(value = "emailinfo",method = RequestMethod.POST)
    public String emailinfo(String userid){
        String result = "";
//        String tablename = "userflaginfo";
//        String rowkey = userid;
//        String famliyname = "baseinfo";
//        String colum = "emailinfo";		// 邮件运营商
//        String result = "";
//        try {
//            result = HbaseServiceImpl.getdata(tablename,rowkey,famliyname,colum);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        result = "qq邮箱用户,网易邮箱用户";
        return result;
    }

    // 商品年度关键词
    @RequestMapping(value = "yearkeyword",method = RequestMethod.POST)
    public String yearkeyword(String userid){
        String result = "";
//        String tablename = "userkeywordlabel";
//        String rowkey = userid;
//        String famliyname = "baseinfo";
//        String colum = "year";
//        String result = "";
//        try {
//            result = HBaseServiceImpl.getdata(tablename,rowkey,famliyname,colum);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        result = "衬衫，皮鞋，电器";
        return result;
    }

    // 商品月度关键词
    @RequestMapping(value = "monthkeyword",method = RequestMethod.POST)
    public String monthkeyword(String userid){
        String tablename = "userkeywordlabel";
        String rowkey = userid;
        String famliyname = "baseinfo";
        String colum = "month";
        String result = "";
        try {
            result = HBaseServiceImpl.getdata(tablename,rowkey,famliyname,colum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 商品季度关键词
    @RequestMapping(value = "quarterkeyword",method = RequestMethod.POST)
    public String quarterkeyword(String userid){
        String tablename = "userkeywordlabel";
        String rowkey = userid;
        String famliyname = "baseinfo";
        String colum = "quarter";
        String result = "";
        try {
            result = HBaseServiceImpl.getdata(tablename,rowkey,famliyname,colum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 性别预测
    @RequestMapping(value = "sex",method = RequestMethod.POST)
    public String sex(String userid){
        String result = "";
//        String tablename = "userflaginfo";
//        String rowkey = userid+"";
//        String famliyname = "baseinfo";
//        String colum = "sex";
//        String result = "";
//        try {
//            result = HBaseServiceImpl.getdata(tablename,rowkey,famliyname,colum);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        result = "女性";
        return result;
    }

	// 用户分群
    @RequestMapping(value = "usergroupinfo",method = RequestMethod.POST)
    public String usergroupinfo(String userid){
            String result = "";
//        String tablename = "userflaginfo";
//        String rowkey = userid;
//        String famliyname = "usergroupinfo";
//        String colum = "usergroupinfo";		// 用户分群信息
//        String result = "";
//        try {
//            result = HBaseServiceImpl.getdata(tablename,rowkey,famliyname,colum);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        result = "中等消费的上班族,偏好于服装";
        return result;
    }

    // 终端偏好
    @RequestMapping(value = "usetypeinfo",method = RequestMethod.POST)
    public String usetypeinfo(String userid){
        String result = "";
//        String tablename = "userflaginfo";
//        String rowkey = userid+"";
//        String famliyname = "userbehavior";
//        String colum = "usetypelist";		// 运营
//        String result = "";
//        try {
//            result = HBaseServiceImpl.getdata(tablename,rowkey,famliyname,colum);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        result = "偏好pc使用者";
        return result;
    }

    // 年代标签-年龄
    @RequestMapping(value = "ageinfo",method = RequestMethod.POST)
    public String ageinfo(String userid){
        String result = "";
//        String tablename = "userflaginfo";
//        String rowkey = userid;
//        String famliyname = "baseinfo";
//        String colum = "age";
//        String result = "";
//        try {
//            result = HBaseServiceImpl.getdata(tablename,rowkey,famliyname,colum);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        result = "28";
        return result;
    }

}
