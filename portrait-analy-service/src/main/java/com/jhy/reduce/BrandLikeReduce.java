package com.jhy.reduce;

import com.jhy.entity.BrandLike;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * 用户品牌偏好标签Reduce
 *      统计下这些用户的品牌偏好
 *
 * Created by JHy on 2019/05/13
 */
public class BrandLikeReduce implements ReduceFunction<BrandLike> {
	@Override
	public BrandLike reduce(BrandLike brandLike, BrandLike t1) throws Exception {
		String brand = brandLike.getBrand();
		long count1 = brandLike.getCount();
		long count2 = t1.getCount();
		BrandLike brandLikefinal = new BrandLike();
		brandLikefinal.setBrand(brand);
		brandLikefinal.setCount(count1+count2);
		return brandLikefinal;
	}
}
