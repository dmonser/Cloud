package ru.netology.Cloud;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(CloudApplication.class, args);
//	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CloudApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}