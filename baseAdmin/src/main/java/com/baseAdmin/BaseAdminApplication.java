package com.baseAdmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baseAdmin.mapper")
public class BaseAdminApplication {
	public static void main(String[] args) {
		
		SpringApplication.run(BaseAdminApplication.class, args);
		
	}

}
