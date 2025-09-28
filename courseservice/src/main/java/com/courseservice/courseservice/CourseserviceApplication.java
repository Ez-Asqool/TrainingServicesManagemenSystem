package com.courseservice.courseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAsync
public class CourseserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseserviceApplication.class, args);
	}
        
        @Bean
        public RestTemplate restTemplate(){
            return new RestTemplate();
        }

}
