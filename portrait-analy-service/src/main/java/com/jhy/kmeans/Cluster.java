package com.jhy.kmeans;

import java.util.ArrayList;
import java.util.List;

/**
 * Kmeans中心类的数据结构（簇）,每个簇Cluster包含多个点Point。
 * Created by li on 2019/5/15.
 */
public class Cluster {
    private int id;												// 簇的唯一标识
    private Point center;										// 中心（簇的中心点的数据）
    private List<Point> members = new ArrayList<Point>();		// 成员（簇下包含哪些点）
 
    public Cluster(int id, Point center) {
        this.id = id;
        this.center = center;
    }
 
    public Cluster(int id, Point center, List<Point> members) {
        this.id = id;
        this.center = center;
        this.members = members;
    }

	/**
	 * 把点加到成员列表里
	 * @param newPoint
	 */
	public void addPoint(Point newPoint) {
        if (!members.contains(newPoint)){
            members.add(newPoint);
        }else{
            System.out.println("样本数据点 {"+newPoint.toString()+"} 已经存在！");
        }
    }
 
    public int getId() {
        return id;
    }
 
    public Point getCenter() {
        return center;
    }
 
    public void setCenter(Point center) {
        this.center = center;
    }
 
    public List<Point> getMembers() {
        return members;
    }
 
    @Override
    public String toString() {
        String toString = "Cluster \n" + "Cluster_id=" + this.id + ", center:{" + this.center.toString()+"}";
        for (Point point : members) {
            toString+="\n"+point.toString();
        }
        return toString+"\n";
    }
}
