package com.hackergames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class BackendServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendServerApplication.class, args);
	}

	@RequestMapping(value = "/pizza")
	public String available() {
		return "pizza route";
	}

	@RequestMapping(value = "/room")
	public String checkedOut() {
		return "room route!";
	}
}
