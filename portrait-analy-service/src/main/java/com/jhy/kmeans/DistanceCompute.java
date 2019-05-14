package com.jhy.kmeans;

public class DistanceCompute {
    /**
     * 求欧式距离
     */
    public double getEuclideanDis(Point p1, Point p2) {
        double count_dis = 0;
        float[] p1_local_array = p1.getlocalArray();
        float[] p2_local_array = p2.getlocalArray();
 
        if (p1_local_array.length != p2_local_array.length) {
            throw new IllegalArgumentException("length of array must be equal!");
        }
 
        for (int i = 0; i < p1_local_array.length; i++) {
            count_dis += Math.pow(p1_local_array[i] - p2_local_array[i], 2);
        }
 
        return Math.sqrt(count_dis);
    }
}
