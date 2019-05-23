package com.jhy.map;

import com.jhy.entity.SexPreInfo;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.Random;

/**
 * SexPreMap性别预测Map
 * 		[构造性别预测模型实体对象]
 *
 * Created by li on 2019/1/6.
 */
public class SexPreMap implements MapFunction<String, SexPreInfo> {

	/**
	 * 性别预测数据维度：
	 * 		用户ID 订单次数 订单频次 浏览男装 浏览小孩 浏览老人 浏览女士 订单平均金额 浏览商品频次 标签
	 * 上面是我们统计出来的数据，作为我们的输入数据、训练这个数据
	 *
	 * @param s
	 * @return
	 * @throws Exception
	 */
	@Override
    public SexPreInfo map(String s) throws Exception {
        String[] temps = s.split("\t");
        Random random = new Random();

        // 清洗以及归一化
        int userid = Integer.valueOf(temps[0]);				// 用户ID
        long ordernum = Long.valueOf(temps[1]);				// 订单的总数（订单次数）
        long orderfre = Long.valueOf(temps[4]);				// 隔多少天下单（订单频次）
        int manclothes =Integer.valueOf(temps[5]);			// 浏览男装次数
        int womenclothes = Integer.valueOf(temps[6]);		// 浏览女装的次数
        int childclothes = Integer.valueOf(temps[7]);		// 浏览小孩衣服的次数
        int oldmanclothes = Integer.valueOf(temps[8]);		// 浏览老人的衣服的次数
        double avramount = Double.valueOf(temps[9]);		// 订单平均金额
		int producttimes = Integer.valueOf(temps[10]);		// 每天浏览商品数（浏览商品频次）
    	int label = Integer.valueOf(temps[11]);				// 0男，1女（标签）

        String fieldgroup = "sexpre=="+random.nextInt(10);
        SexPreInfo sexPreInfo = new SexPreInfo();
        sexPreInfo.setUserid(userid);
        sexPreInfo.setOrdernum(ordernum);
        sexPreInfo.setOrderfre(orderfre);
        sexPreInfo.setManclothes(manclothes);
        sexPreInfo.setWomenclothes(womenclothes);
        sexPreInfo.setChildclothes(childclothes);
        sexPreInfo.setOldmanclothes(oldmanclothes);
        sexPreInfo.setAvramount(avramount);
        sexPreInfo.setProducttimes(producttimes);
        sexPreInfo.setLabel(label);
        sexPreInfo.setGroupfield(fieldgroup);
        return sexPreInfo;
    }
}
