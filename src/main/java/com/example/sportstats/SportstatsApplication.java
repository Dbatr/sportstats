package com.example.sportstats;

import com.example.sportstats.services.CsvDataService;
import com.example.sportstats.services.PlayerGraphicsService;
import com.example.sportstats.services.PlayerStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class SportstatsApplication implements CommandLineRunner {

	@Autowired
	private CsvDataService csvDataService;

	@Autowired
	private PlayerStatsService playerStatsService;

	@Autowired
	private PlayerGraphicsService playerGraphicsService;

	public static void main(String[] args) {
		SpringApplication.run(SportstatsApplication.class, args);
	}

	@Override
	public void run(String... args) throws IOException {
		String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource("performance_teams.csv")).getPath();
		csvDataService.importDataFromCsv(filePath);

		// Находим команду с самым высоким средним ростом
		String teamWithHighestAverageHeight = playerStatsService.findTeamWithHighestAverageHeight();

		// Выводим 5 самых высоких игроков этой команды
		if (teamWithHighestAverageHeight != null) {
			playerStatsService.printTop5TallestPlayers(teamWithHighestAverageHeight);
		}

		playerStatsService.findTeamWithHighestAverageAgeInRange();

		// Генерируем и сохраняем график среднего возраста в командах
		playerGraphicsService.generateAgeChart();
		playerGraphicsService.generateAgePieChart();

		System.exit(0);
	}
}
