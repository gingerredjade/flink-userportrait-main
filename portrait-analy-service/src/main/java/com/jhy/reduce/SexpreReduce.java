package com.jhy.reduce;

import com.jhy.entity.SexPreInfo;
import com.jhy.logic.CreateDataSet;
import com.jhy.logic.Logistic;
import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by li on 2019/1/6.
 */
public class SexpreReduce implements GroupReduceFunction<SexPreInfo,ArrayList<Double>> {
    @Override
    public void reduce(Iterable<SexPreInfo> iterable, Collector<ArrayList<Double>> collector) throws Exception {
        Iterator<SexPreInfo> iterator = iterable.iterator();
        CreateDataSet trainingSet = new CreateDataSet();
        while(iterator.hasNext()){
            SexPreInfo sexPreInfo = iterator.next();
            int userid = sexPreInfo.getUserid();
            long ordernum = sexPreInfo.getOrdernum();				// 订单的总数
            long orderfre = sexPreInfo.getOrderfre();				// 隔多少天下单
            int manclothes = sexPreInfo.getManclothes();			// 浏览男装次数
            int womenclothes = sexPreInfo.getWomenclothes();		// 浏览女装的次数
            int childclothes = sexPreInfo.getChildclothes();		// 浏览小孩衣服的次数
            int oldmanclothes = sexPreInfo.getOldmanclothes();		// 浏览老人的衣服的次数
            double avramount = sexPreInfo.getAvramount();			// 订单平均金额
            int producttimes = sexPreInfo.getProducttimes();		// 每天浏览商品数
            int label = sexPreInfo.getLabel();						// 0男，1女


            ArrayList<String> as = new ArrayList<String>();
            as.add(ordernum+"");
            as.add(orderfre+"");
            as.add(manclothes+"");

            as.add(womenclothes+"");
            as.add(childclothes+"");
            as.add(oldmanclothes+"");

            as.add(avramount+"");
            as.add(producttimes+"");

            trainingSet.data.add(as);
            trainingSet.labels.add(label+"");
        }
        ArrayList<Double> weights = new ArrayList<Double>();
        weights = Logistic.gradAscent1(trainingSet, trainingSet.labels, 500);
        collector.collect(weights);
    }
}
