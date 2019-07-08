package com.tong.sevencommon.mutiplydatasource;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author: SevenTian
 * @Description: 动态切换数据源
 * @Create_Date: 14:48 2019/7/4
 * @Modified_Author:
 * @Modified_Date:
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
