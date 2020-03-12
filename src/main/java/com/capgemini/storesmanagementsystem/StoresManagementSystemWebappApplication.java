package com.capgemini.storesmanagementsystem;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoresManagementSystemWebappApplication {
	
	static Logger log = LogManager.getLogger("chandu");
	
	public static void main(String[] args) {
		SpringApplication.run(StoresManagementSystemWebappApplication.class, args);
	}

}
