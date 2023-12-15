package com.example.sportstats.services;
import com.example.sportstats.Entity.PlayerInfo;
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

        System.out.println("\n"+"\n"+"Team with highest average height: " + teamName + " (Average Height: " + averageHeight + ")"+"\n"+"\n");
        return teamName;
    }

    public void printTop5TallestPlayers(String teamName) {
        // Создаем объект Pageable для вывода 5 элементов
        Pageable pageable = PageRequest.of(0, 5);
        List<PlayerInfo> top5TallestPlayers = playerInfoRepository.findTop5TallestPlayersByTeam(teamName, pageable);

        if (top5TallestPlayers.isEmpty()) {
            System.out.println("No players found for the team: " + teamName);
        } else {
            System.out.println("\n"+"\n"+"Top 5 tallest players from team " + teamName + ":");
            for (PlayerInfo player : top5TallestPlayers) {
                System.out.println(player.getName() + " - Height: " + player.getHeight());
            }
            System.out.println("\n"+"\n");
        }
    }

    public void findTeamWithHighestAverageAgeInRange() {
        List<Object[]> teamList = playerInfoRepository.findTeamWithHighestAverageAgeInRange();

        if (teamList.isEmpty()) {
            System.out.println("Таких команд нет");
        } else {
            Object[] teamWithHighestAverageAge = teamList.get(0);
            String teamName = (String) teamWithHighestAverageAge[0];
            double averageHeight = (Double) teamWithHighestAverageAge[1];
            double averageWeight = (Double) teamWithHighestAverageAge[2];
            double averageAge = (Double) teamWithHighestAverageAge[3];

            System.out.println("\n"+"\n");
            System.out.println("Команда с самым высоким средним возрастом, в котором средний рост от 74 до 78 inches и средний вес от 190 до 210 lbs:");
            System.out.println("Название команды: " + teamName);
            System.out.println("Средний рост команды: " + averageHeight);
            System.out.println("Средний вес команды: " + averageWeight);
            System.out.println("Средний возраст команды: " + averageAge);
            System.out.println("\n"+"\n");
        }

        //Здесь можно посмотреть весь список:
//        System.out.println("Список команд согласно заданным критериям:");
//        for (Object[] team : teamList) {
//            String teamName = (String) team[0];
//            double averageHeight = (Double) team[1];
//            double averageWeight = (Double) team[2];
//            double averageAge = (Double) team[3];
//
//            System.out.println("-------------");
//            System.out.println("-------------");
//            System.out.println("Название команды: " + teamName);
//            System.out.println("Средний рост команды: " + averageHeight);
//            System.out.println("Средний вес команды: " + averageWeight);
//            System.out.println("Средний возраст команды: " + averageAge);
//            System.out.println("-------------");
//            System.out.println("-------------");
//        }
    }
}
