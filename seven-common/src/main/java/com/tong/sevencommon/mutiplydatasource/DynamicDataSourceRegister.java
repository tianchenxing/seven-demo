package com.tong.sevencommon.mutiplydatasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: SevenTian
 * @Description:  初始化数据源和提供了执行动态切换数据源的工具类
 * @Create_Date: 14:52 2019/7/4
 * @Modified_Author:
 * @Modified_Date:
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    //指定默认数据源(springboot2.0默认数据源是hikari如何想使用其他数据源可以自己配置)
    private static final String DATASOURCE_TYPE_DEFAULT = "com.zaxxer.hikari.HikariDataSource";
    //默认数据源
    private DataSource defaultDataSource;
    //用户自定义数据源
    private Map<String, DataSource> customDataSources = new HashMap<String, DataSource>();


    @Override
    public void setEnvironment(Environment environment) {
        initDefaultDataSource(environment);
        initCustomDataSources(environment);
    }
    /**
     * 初始化主数据源
     *
     */
    private void initDefaultDataSource(Environment env) {
         // 读取主数据源
        Map<String, Object> dsMap = new HashMap<>();
        dsMap.put("driver-class-name", env.getProperty("spring.datasource.driver-class-name"));
        dsMap.put("url", env.getProperty("spring.datasource.url"));
        dsMap.put("username", env.getProperty("spring.datasource.username"));
        dsMap.put("password", env.getProperty("spring.datasource.password"));
        defaultDataSource = buildDataSource(dsMap);
    }
    /**
     * 创建DataSource
     * @param dataSourceMap
     * @return
     */
    @SuppressWarnings("unchecked")
    public DataSource buildDataSource(Map<String, Object> dataSourceMap) {
        try {
            Object type = dataSourceMap.get("type");
            if (type == null) {
                type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource
            }
            Class<? extends DataSource> dataSourceType;
            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);
            String driverClassName = dataSourceMap.get("driver-class-name").toString();
            String url = dataSourceMap.get("url").toString();
            String username = dataSourceMap.get("username").toString();
            String password = dataSourceMap.get("password").toString();
            // 自定义DataSource配置
            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
                    .username(username).password(password).type(dataSourceType);
            return factory.build();
        } catch (ClassNotFoundException e) {
            return null;
        }

    }

    /**
     * 初始化更多数据源
     */
    private void initCustomDataSources(Environment env) {
        // 读取配置文件获取更多数据源
        String dsPrefixs = env.getProperty("spring.datasource.names");

        for (String dsPrefix : dsPrefixs.split(",")) {
            // 多个数据源
            Map<String, Object> dsMap = new HashMap<>();
            dsMap.put("driver-class-name", env.getProperty("spring.datasource." + dsPrefix + ".driver-class-name"));
            dsMap.put("url", env.getProperty("spring.datasource." + dsPrefix + ".url"));
            dsMap.put("username", env.getProperty("spring.datasource." + dsPrefix + ".username"));
            dsMap.put("password", env.getProperty("spring.datasource." + dsPrefix + ".password"));
            DataSource ds = buildDataSource(dsMap);
            customDataSources.put(dsPrefix, ds);
        }

    }
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,BeanDefinitionRegistry beanDefinitionRegistry) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        //添加默认数据源
        targetDataSources.put("dataSource", this.defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        //添加其他数据源
        targetDataSources.putAll(customDataSources);
        for (String key : customDataSources.keySet()) {
            DynamicDataSourceContextHolder.dataSourceIds.add(key);
        }

        //创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        //注册 - BeanDefinitionRegistry
        beanDefinitionRegistry.registerBeanDefinition("dataSource", beanDefinition);

        logger.info("Dynamic DataSource Registry");

    }
}
