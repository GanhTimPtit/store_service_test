package com.ptit.edu.store;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StoreApplication {
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}


//	@Bean
//	public static PropertyPlaceholderConfigurer properties() {
//		PropertyPlaceholderConfigurer ppc
//				= new PropertyPlaceholderConfigurer();
////		Resource[] resources = new ClassPathResource[]
////				{ new ClassPathResource( "validator.properties" ) };
////		ppc.setLocations( resources );
//		ppc.setIgnoreUnresolvablePlaceholders( true );
//		return ppc;
//	}
	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
