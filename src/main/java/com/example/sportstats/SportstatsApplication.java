package com.example.sportstats;

import com.example.sportstats.services.CsvDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SportstatsApplication implements CommandLineRunner {

	@Autowired
	private CsvDataService csvDataService;

	public static void main(String[] args) {
		SpringApplication.run(SportstatsApplication.class, args);
	}

	@Override
	public void run(String... args) throws IOException {
		String filePath = "C:\\Users\\Lenovo\\Desktop\\sportstats\\src\\main\\resources\\performance_teams.csv";
		csvDataService.importDataFromCsv(filePath);
	}
}
