package com.example.mongodbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MongodbdemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MongodbdemoApplication.class, args);
	}
	
	 @Override
 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
  return application.sources(DeployableWarApplication.class);
 }


}
