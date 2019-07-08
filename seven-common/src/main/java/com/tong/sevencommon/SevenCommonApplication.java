package com.tong.sevencommon;


import com.tong.sevencommon.mutiplydatasource.DynamicDataSourceRegister;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.tong.sevencommon.mapper"})//通用Mapper范围
@Import({DynamicDataSourceRegister.class})//动态数据源
public class SevenCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SevenCommonApplication.class, args);
    }

}
