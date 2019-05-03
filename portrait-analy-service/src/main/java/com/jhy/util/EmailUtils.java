package com.jhy.util;

/**
 * 邮箱工具类
 * Created by JHy on 2019/04/25
 */
public class EmailUtils {

    /**
     * 根据邮箱地址判断其属于哪家邮箱
     *
     *  网易邮箱 @163.com @126.com
     移动邮箱 @139.com
     搜狐邮箱 @sohu.com
     qq邮箱  @qq.com
     189邮箱 @189.cn
     tom邮箱 @tom.com
     阿里邮箱 @aliyun.com
     新浪邮箱 @sina.com
     等等
     * @param email
     * @return
     */
    public static String getEmailtypeBy(String email){
        String emailtye = "其他邮箱用户";
        if(email.contains("@163.com")||email.contains("@126.com")){
            emailtye = "网易邮箱用户";
        }else if (email.contains("@139.com")){
            emailtye = "移动邮箱用户";
        }else if (email.contains("@sohu.com")){
            emailtye = "搜狐邮箱用户";
        }else if (email.contains("@qq.com")){
            emailtye = "qq邮箱用户";
        }else if (email.contains("@189.cn")){
            emailtye = "189邮箱用户";
        }else if (email.contains("@tom.com")){
            emailtye = "tom邮箱用户";
        }else if (email.contains("@aliyun.com")){
            emailtye = "阿里邮箱用户";
        }else if (email.contains("@sina.com")){
            emailtye = "新浪邮箱用户";
        }
        return emailtye;
    }

}
