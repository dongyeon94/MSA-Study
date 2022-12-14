package com.coffee.msa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MSAEurekaServer {

	public static void main(String[] args) {
		SpringApplication.run(MSAEurekaServer.class, args);
	}

}
