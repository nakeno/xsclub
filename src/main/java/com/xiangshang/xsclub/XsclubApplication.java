package com.xiangshang.xsclub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.xiangshang")
@MapperScan("com.xiangshang.xsclub.db.dao")
@SpringBootApplication
public class XsclubApplication {

	public static void main(String[] args) {
		SpringApplication.run(XsclubApplication.class, args);
	}
}
