package com.hss.hdfs;

import com.hss.constant.HadoopConfig;
import org.apache.hadoop.conf.Configuration;
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
        conf = new Configuration();
        fs = FileSystem.get(new URI(HadoopConfig.URL),conf,HadoopConfig.USER);
        System.out.println("===========create==========");
    }

    @Test
    public void mkdir() {
        try {
            Path dir = new Path("/usr/root/java8-maven");
            if(fs.exists(dir)){
                fs.delete(dir,true);
            }
            Boolean flag = fs.mkdirs(dir);
            System.out.println(flag);
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
