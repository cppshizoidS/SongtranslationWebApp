package ru.app.ccfit.cppshiz;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.nsu.ccfit.trubitsyna"})
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Override
	public String toString() {
		return "MainApplication []";
	}

}
