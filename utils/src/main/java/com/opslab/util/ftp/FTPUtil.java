package com.opslab.util.ftp;


import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * FTP工具类
 *
 * @require apache-commons-net.jar.
 */
public interface FTPUtil {
    //判断远程文件是否存在
	boolean isExists(String fileName);

    //下载远程文件
	boolean downLoad(String fileName);

    //下载远程目录
	boolean downLoadDir(String directory);

    //删除远程文件
	boolean deleteFile(String fileName);

    //删除远程目录
	boolean deleteDir(String directory);

    //上传本地文件到远程目录
	boolean putFile(String fileName, String remoteFileName, boolean isDelete);

    //上传本地文件到远程目录
	boolean putFile(File file, String remoteFileName, boolean isDelete);

    //上传本地目录到远程
	boolean putDir(String fileName, String remoteDir);

    //上传本地目录到远程
	boolean putDir(File file, String remoteDir);

    //创建文件夹
	boolean mkDir(String destory);


    //获取远程文件列表
	List<String> listFile(String directory);

    //获取远程文件夹的目录结构
	LinkedList<String> listDir(String direcotyr);

    //获取远程文件属性以Map形式返回
	Map<String, FileAttr> listFileAttr(String directory);

    //改变FTP连接的工作目录
	boolean changeWorkDir(String directory);

    //获取当前连接的工作目录
	String getWorkDir();

    //重命名文件
	boolean changName(String oldName, String newName);

    //返回FTPCliend对象(已经打开连接)
	FTPClient client();

    //释放所有的资源
	void destory();
}
