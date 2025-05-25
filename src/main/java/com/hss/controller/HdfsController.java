package com.hss.controller;

import com.hss.constant.HadoopConfig;
import com.hss.hdfs.TestHDFS;
import org.apache.hadoop.fs.Path;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author Hss
 * @date 2025-03-30
 */
@RestController
@RequestMapping(value = "/hdfs")
public class HdfsController {

    @RequestMapping(value = "/upload")
    public String upload(String localPath){
        TestHDFS testHDFS = new TestHDFS();
        try {
            testHDFS.conn();
            Path putFile = new Path(localPath);
            Path outFile = new Path(HadoopConfig.URL + "/usr/root222/hello.txt");
            testHDFS.fs.deleteOnExit(outFile);
            testHDFS.fs.copyFromLocalFile(false, true, putFile, outFile);
            testHDFS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }
}
