package com.fcmis.fcmis_API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.fcmis.fcmis_API.domain")
@EnableJpaRepositories("com.fcmis.fcmis_API.repo")
public class FcmisApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FcmisApiApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FcmisApiApplication.class);
	}
}
