package com.hss.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


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
        conf = new Configuration(true);
        fs = FileSystem.get(conf);
        System.out.println("===========create==========");
    }

    @Test
    public void mkdir() {
        try {
            Boolean flag = fs.mkdirs(new Path("/java8-maven"));
            System.out.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() throws Exception {
        System.out.println("===========close==========");
        if(null != fs){
            fs.close();
        }
        if(null != conf){
            conf = null;
        }
    }
}
