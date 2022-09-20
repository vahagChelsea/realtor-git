package com.Game.Game;

import com.Game.Game.models.User;
import com.Game.Game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class GameApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}

	@Bean
	public CommandLineRunner CommandLineRunnerBean() {
		return (args) -> {
			User user=new User();
			user.setPassword("123");
			user.setUsername("admin");
			userService.saveUser(user);
		};
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/article/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");


			}
		};
	}
}
