package com.yaozhou.permission;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Yao.Zhou
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.yaozhou.permission.mapper")
public class PermissionApplication {

    public static void main(String[] args) {
        SpringApplication.run(PermissionApplication.class, args);
    }

}
