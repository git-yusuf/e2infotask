package com.yusuf.e2infotask;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class E2infotaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(E2infotaskApplication.class, args);
	}

}
