package com.jhy.util;

import java.util.regex.Pattern;

/**
 * 运营商工具类
 * Created by JHy on 2019/04/25
 */
public class CarrierUtils {

    /**
     * 中国电信号码格式验证 手机段： 133,153,180,181,189,177,1700,173,199
     **/
    private static final String CHINA_TELECOM_PATTERN = "(^1(33|53|77|73|99|8[019])\\d{8}$)|(^1700\\d{7}$)";

    /**
     * 中国联通号码格式验证 手机段：130,131,132,155,156,185,186,145,176,1709
     **/
    private static final String CHINA_UNICOM_PATTERN = "(^1(3[0-2]|4[5]|5[56]|7[6]|8[56])\\d{8}$)|(^1709\\d{7}$)";

    /**
     * 中国移动号码格式验证
     * 手机段：134,135,136,137,138,139,150,151,152,157,158,159,182,183,184,187,188,147,178,1705
     **/
    private static final String CHINA_MOBILE_PATTERN = "(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(^1705\\d{7}$)";


    /**
     * 根据手机号前三位判断所属运营商
     * 0、未知 1、移动 2、联通 3、电信
     * @param telphone
     * @return
     */
    public static int getCarrierByTel(String telphone){
        boolean b1 = telphone == null || telphone.trim().equals("") ? false : match(CHINA_MOBILE_PATTERN, telphone);
        if (b1) {
            return 1;
        }
        b1 = telphone == null || telphone.trim().equals("") ? false : match(CHINA_UNICOM_PATTERN, telphone);
        if (b1) {
            return 2;
        }
        b1 = telphone == null || telphone.trim().equals("") ? false : match(CHINA_TELECOM_PATTERN, telphone);
        if (b1) {
            return 3;
        }
        return 0;
    }

    /**
     * 匹配函数
     * @param regex
     * @param tel
     * @return
     */
    private static boolean match(String regex, String tel) {
        return Pattern.matches(regex, tel);
    }

}
