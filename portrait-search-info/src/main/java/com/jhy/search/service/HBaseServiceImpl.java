package com.jhy.search.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * HBase服务类
 * Created by JHy on 2019/5/19.
 */
@Service
public class HBaseServiceImpl {
    private static Admin admin = null;
    private static Connection conn = null;

    // Windows环境运行HBase所依赖的Hadoop文件'winutils.exe'
	private static String WINUTILS_FILE_DIRECTORY = "";

    static{
		String userDir = System.getProperty("user.dir");
		WINUTILS_FILE_DIRECTORY = userDir + File.separator + "hadoopwinutils";
		System.setProperty("hadoop.home.dir", WINUTILS_FILE_DIRECTORY);

        // 创建HBase配置对象
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.rootdir","hdfs://192.168.75.20:9000/hbase");
        // 使用eclipse时必须添加这个，否则无法定位
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
     * 读取HBase指定rowkey、familyname、colum数据
     */
    public static String getdata(String tablename, String rowkey, String familyname,String colum) throws Exception {
        Table table = conn.getTable(TableName.valueOf(tablename));
        // 将字符串转换成byte[]
        byte[] rowkeybyte = Bytes.toBytes(rowkey);
        Get get = new Get(rowkeybyte);
        Result result =table.get(get);
        byte[] resultbytes = result.getValue(familyname.getBytes(),colum.getBytes());
        if(resultbytes == null){
            return null;
        }

        return new String(resultbytes);
    }
}
