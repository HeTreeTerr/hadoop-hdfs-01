package com.hss.hdfs;

import com.hss.constant.HadoopConfig;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
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

    @Test
    public void upload(){
        try {
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(new File("./data/hello.txt")));
            Path outFile = new Path("/usr/root/hello-out.txt");
            FSDataOutputStream output = fs.create(outFile);

            IOUtils.copyBytes(input,output,conf,true);
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
