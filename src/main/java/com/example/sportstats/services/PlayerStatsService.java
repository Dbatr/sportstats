package com.example.sportstats.services;
import com.example.sportstats.Entity.PlayerDetails;
import com.example.sportstats.Entity.PlayerInfo;
import com.example.sportstats.repositories.PlayerDetailsRepository;
import com.example.sportstats.repositories.PlayerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class PlayerStatsService {

    @Autowired
    private PlayerInfoRepository playerInfoRepository;


    public String findTeamWithHighestAverageHeight() {
        List<Object[]> teamAverageHeightList = playerInfoRepository.findTeamAverageHeight();
        if (teamAverageHeightList.isEmpty()) {
            return null;
        }

        // Находим команду с самым высоким средним ростом
        Object[] teamWithHighestAverageHeight = teamAverageHeightList.get(0);
        String teamName = (String) teamWithHighestAverageHeight[0];
        double averageHeight = (Double) teamWithHighestAverageHeight[1];

        System.out.println("Team with highest average height: " + teamName + " (Average Height: " + averageHeight + ")");
        return teamName;
    }

    public void printTop5TallestPlayers(String teamName) {

        // Создаем объект Pageable для запроса первой страницы с 5 элементами
        Pageable pageable = PageRequest.of(0, 5);

        List<PlayerInfo> top5TallestPlayers = playerInfoRepository.findTop5TallestPlayersByTeam(teamName, pageable);

        if (top5TallestPlayers.isEmpty()) {
            System.out.println("No players found for the team: " + teamName);
        } else {
            System.out.println("Top 5 tallest players from team " + teamName + ":");
            for (PlayerInfo player : top5TallestPlayers) {
                System.out.println(player.getName() + " - Height: " + player.getHeight());
            }
        }
    }
}
