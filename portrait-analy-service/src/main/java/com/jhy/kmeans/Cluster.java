package com.jhy.kmeans;

import java.util.ArrayList;
import java.util.List;
 
public class Cluster {
    private int id;										// 标识
    private Point center;								// 中心
    private List<Point> members = new ArrayList<Point>();	// 成员
 
    public Cluster(int id, Point center) {
        this.id = id;
        this.center = center;
    }
 
    public Cluster(int id, Point center, List<Point> members) {
        this.id = id;
        this.center = center;
        this.members = members;
    }
 
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
