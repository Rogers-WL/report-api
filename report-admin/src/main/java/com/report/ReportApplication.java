package com.report;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动程序
 *
 * @author rogers
 */
@MapperScan(value = {"com.report.**.mapper"})
// (scanBasePackages = {"com.report"})
@SpringBootApplication (scanBasePackages = {"com.report"})
@EnableScheduling
public class ReportApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReportApplication.class, args);
    }
}
