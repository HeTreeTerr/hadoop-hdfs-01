package com.hss.constant;

/**
 * <p>
 *  Hadoop 配置
 * </p>
 *
 * @author Hss
 * @date 2025-03-16
 */
public interface HadoopConfig {
    /**
     * 连接地址
     */
    String URL = "hdfs://namenode:8020";

    /**
     * 用户
     */
    String USER = "root";

    /**
     * 副本
     */
    String REPLICATION = "1";
}
