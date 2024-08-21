package com.prajwal.joinmyride1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan({"com.prajwal.joinmyride1.query","com.prajwal.joinmyride1.mutation", "com.prajwal.joinmyride1.service","com.prajwal.joinmyride1.Repository","com.prajwal.joinmyride1.config","com.prajwal.joinmyride1.util","com.prajwal.joinmyride1.dto"})
@EntityScan("com.prajwal.joinmyride1.entity")
@EnableJpaRepositories("com.prajwal.joinmyride1.Repository")
public class Joinmyride1Application {

	public static void main(String[] args) {
		SpringApplication.run(Joinmyride1Application.class, args);
	}

}
