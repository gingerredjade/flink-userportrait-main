package com.jhy.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * HBase工具类
 * Created by JHy on 2019/04/25
 */
public class HBaseUtils {

    private static Admin admin = null;
    private static Connection conn = null;
    static{
        // 创建HBase配置对象
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.rootdir","hdfs://192.168.75.20:9000/hbase");
        //使用eclipse时必须添加这个，否则无法定位
        conf.set("hbase.zookeeper.quorum","192.168.75.20");
        conf.set("hbase.client.scanner.timeout.period", "600000");
        conf.set("hbase.rpc.timeout", "600000");
        try {
            conn = ConnectionFactory.createConnection(conf);
            // 得到管理程序
            admin = conn.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 插入数据（支持批量操作）
     * create "userflaginfo,"baseinfo"（用户信息，基础信息）
     * create "tfidfdata,"baseinfo"（）
     */
    public static void put(String tablename, String rowkey, String famliyname, Map<String,String> datamap) throws Exception {
        Table table = conn.getTable(TableName.valueOf(tablename));
        // 将字符串转换成byte[]
        byte[] rowkeybyte = Bytes.toBytes(rowkey);
        Put put = new Put(rowkeybyte);
        if(datamap != null){
            Set<Map.Entry<String,String>> set = datamap.entrySet();
            for(Map.Entry<String,String> entry : set){
                String key = entry.getKey();
                Object value = entry.getValue();
                put.addColumn(Bytes.toBytes(famliyname), Bytes.toBytes(key), Bytes.toBytes(value+""));
            }
        }
        table.put(put);
        table.close();
        System.out.println("ok");
    }

    /**
     * 获取数据，get 'tablename','rowkey'
     */
    public static String getdata(String tablename, String rowkey, String famliyname, String colum) throws Exception {
        Table table = conn.getTable(TableName.valueOf(tablename));
        // 将字符串转换成byte[]
        byte[] rowkeybyte = Bytes.toBytes(rowkey);
        Get get = new Get(rowkeybyte);
        Result result =table.get(get);
        byte[] resultbytes = result.getValue(famliyname.getBytes(),colum.getBytes());
        if(resultbytes == null){
            return null;
        }

        return new String(resultbytes);
    }

    /**
     * 插入数据（插入一条数据）
     */
    public static void putdata(String tablename, String rowkey, String famliyname, String colum, String data) throws Exception {
        Table table = conn.getTable(TableName.valueOf(tablename));
        Put put = new Put(rowkey.getBytes());
        put.addColumn(famliyname.getBytes(),colum.getBytes(),data.getBytes());
        table.put(put);
    }

    /**
     * 创建表
     */
    public void createTable(String tabName,String famliyname) throws Exception {
        HTableDescriptor tab = new HTableDescriptor(tabName);
        // 添加列族,每个表至少有一个列族.
        HColumnDescriptor colDesc = new HColumnDescriptor(famliyname);
        tab.addFamily(colDesc);
        // 创建表
        admin.createTable(tab);
        System.out.println("over");
    }


}
