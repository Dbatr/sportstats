package com.example.sportstats.services;

import com.example.sportstats.Entity.Player;
import com.example.sportstats.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class CsvDataService {
    @Autowired
    private PlayerRepository playerRepository;

    public void importDataFromCsv(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Пропустим первую строку (заголовок)
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Player player = new Player();
                player.setName(data[0]);
                player.setTeam(data[1].replaceAll("\"", "").trim());
                player.setPosition(data[2].replaceAll("\"", ""));
                player.setHeight(Integer.parseInt(data[3]));
                player.setWeight(Integer.parseInt(data[4]));
                player.setAge(Double.parseDouble(data[5]));

                playerRepository.save(player);
            }
        }
    }
}
