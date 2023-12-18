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

	/**
	 * Метод, который выполняется при запуске приложения.
	 *
	 * @param args Аргументы командной строки.
	 * @throws IOException при ошибках ввода-вывода
	 */
	@Override
	public void run(String... args) throws IOException {
		// Получаем путь к файлу CSV из ресурсов
		String filePath = Objects.requireNonNull(getClass()
												.getClassLoader()
												.getResource("performance_teams.csv"))
												.getPath();

		try {
			// Импортируем данные из CSV файла в базу данных
			csvDataService.importDataFromCsv(filePath);

			// Находим команду с самым высоким средним ростом
			String teamWithHighestAverageHeight = playerStatsService.findTeamWithHighestAverageHeight();

			// Выводим 5 самых высоких игроков этой команды
			if (teamWithHighestAverageHeight != null) {
				playerStatsService.printTop5TallestPlayers(teamWithHighestAverageHeight);
			}

			// Находим команду с самым высоким средним возрастом в заданном диапазоне
			playerStatsService.findTeamWithHighestAverageAgeInRange();

			// Генерируем и сохраняем график среднего возраста в командах
			playerGraphicsService.generateAgeChart();
			playerGraphicsService.generateAgePieChart();

		} catch (IOException e) {
			// Логирование ошибки или обработка исключения ввода-вывода
			System.err.println("Ошибка при выполнении приложения: " + e.getMessage());
			throw e;
		}
		// Выход из приложения с кодом 0
		System.exit(0);
	}
}
