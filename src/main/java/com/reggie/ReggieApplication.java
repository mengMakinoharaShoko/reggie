package com.reggie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author YX200
 */
@MapperScan("com.reggie.mapper")
@ServletComponentScan(basePackages = "com.reggie")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableTransactionManagement
public class ReggieApplication {

    public static void main(String[] args) {

        SpringApplication.run(ReggieApplication.class, args);
    }

}
