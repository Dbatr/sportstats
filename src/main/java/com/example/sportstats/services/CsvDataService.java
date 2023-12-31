package com.example.sportstats.services;

import com.example.sportstats.Entity.PlayerDetails;
import com.example.sportstats.Entity.PlayerInfo;
import com.example.sportstats.repositories.PlayerDetailsRepository;
import com.example.sportstats.repositories.PlayerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class CsvDataService {
    @Autowired
    private PlayerDetailsRepository playerDetailsRepository;

    @Autowired
    private PlayerInfoRepository playerInfoRepository;

    /**
     * Импортирует данные из CSV-файла и сохраняет их в базе данных.
     *
     * @param filePath Путь к CSV-файлу.
     */
    public void importDataFromCsv(String filePath) {

        // Проверяем, есть ли уже записи в базе данных
        if (playerInfoRepository.count() > 0 && playerDetailsRepository.count() > 0) {
            System.out.println("""


                    База данных не пуста. Новые данные не добавлены.


                    """);
        }
        else {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                // Пропустим первую строку (заголовок)
                br.readLine();

                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        String[] data = line.split(",");

                        PlayerDetails playerDetails = new PlayerDetails();
                        playerDetails.setName(data[0]);
                        playerDetails.setHeight(Integer.parseInt(data[3]));
                        playerDetails.setWeight(Integer.parseInt(data[4]));
                        playerDetails.setAge(Double.parseDouble(data[5]));

                        PlayerInfo playerInfo = new PlayerInfo();
                        playerInfo.setTeam(data[1].replaceAll("\"", "").trim());
                        playerInfo.setPosition(data[2].replaceAll("\"", ""));

                        playerDetails.setInfo(playerInfo);

                        // Сохраняем PlayerDetails и PlayerInfo
                        playerInfoRepository.save(playerInfo);
                        playerDetailsRepository.save(playerDetails);

                    } catch (NumberFormatException e) {
                        // Логирование ошибки или обработка исключения при парсинге чисел
                        System.err.println("Ошибка при парсинге чисел из CSV: " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                // Логирование ошибки или обработка исключения ввода-вывода
                System.err.println("Ошибка при чтении файла CSV: " + e.getMessage());
            }
        }
    }
}
