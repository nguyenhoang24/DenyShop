package com.laptrinhweb.denyweb;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DenyShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(DenyShopApplication.class, args);
		System.out.println("RA DỒI");
		Date todayDate = new Date();
		System.out.println("ngày: " + todayDate);
	}

}
