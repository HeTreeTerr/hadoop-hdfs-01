package com.hss.hdfs;

import com.hss.constant.HadoopConfig;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;


/**
 * <p>
 *
 * </p>
 *
 * @author Hss
 * @date 2025-03-16
 */
public class TestHDFS {

    public Configuration conf = null;

    public FileSystem fs = null;

    @Before
    public void conn() throws Exception {
        //环境变量
        System.setProperty("HADOOP_USER_NAME", HadoopConfig.USER);
        System.setProperty("hadoop.home.dir", "D:\\software\\hadoop-2.6.5");
        System.setProperty("hadoop.root.logger", "DEBUG,console");
        //创建HADOOP配置对象
        conf = new Configuration();
        conf.set("fs.defaultFS",HadoopConfig.URL);
        conf.set("dfs.replication",HadoopConfig.REPLICATION);

        //conf.set("dfs.client.pipeline", "cluster01:50010");
        // 设置超时时间
        conf.set("dfs.client.socket-timeout", "300000");
        conf.set("dfs.datanode.socket.write.timeout", "300000");
        //创建HDFS客户端
        fs = FileSystem.get(new URI(HadoopConfig.URL),conf,HadoopConfig.USER);
        System.out.println("===========create==========");
    }

    @Test
    public void mkdir() {
        try {
            Path dir = new Path("/root/tempFile");
            if(fs.exists(dir)){
                fs.delete(dir,true);
            }
            Boolean flag = fs.mkdirs(dir);
            System.out.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void upload(){
        try {
            Path putFile = new Path("E:/data/hello.txt");
            Path outFile = new Path(HadoopConfig.URL + "/usr/root222/hello.txt");
            fs.deleteOnExit(outFile);
            fs.copyFromLocalFile(false, true, putFile, outFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        try {
            Path dir = new Path("/usr/root222/hello.txt");
            Boolean flag = false;
            if(fs.exists(dir)){
                flag = fs.delete(dir,true);
            }
            System.out.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllFile(){
        try {
            // 示例：列出 HDFS 根目录下的文件
            Path rootPath = new Path("/");
            FileStatus[] files = fs.listStatus(rootPath);
            for (FileStatus file : files) {
                System.out.println("File: " + file.getPath().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() throws Exception {
        System.out.println("===========close==========");
        fs.close();
    }
}
