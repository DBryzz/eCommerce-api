package com.gg.ecom;

import com.gg.ecom.service.FileStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class ECommerceApiApplication implements CommandLineRunner {

	@Resource
	FileStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApiApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}
}
