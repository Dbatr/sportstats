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

    /**
     * Находит команду с самым высоким средним ростом.
     *
     * @return Название команды с самым высоким средним ростом или null, если список пуст.
     */
    public String findTeamWithHighestAverageHeight() {
        List<Object[]> teamAverageHeightList = playerInfoRepository.findTeamAverageHeight();
        if (teamAverageHeightList.isEmpty()) {
            return null;
        }
        // Находим команду с самым высоким средним ростом
        Object[] teamWithHighestAverageHeight = teamAverageHeightList.get(0);
        String teamName = (String) teamWithHighestAverageHeight[0];
        double averageHeight = (Double) teamWithHighestAverageHeight[1];

        System.out.println("-------------");
        System.out.println("-------------");
        System.out.println("Команда с самым высоким средним ростом: " + teamName +
                            " (Средний рост: " + averageHeight + ")"+"\n"+"\n");
        return teamName;
    }

    /**
     * Выводит 5 самых высоких игроков для указанной команды.
     *
     * @param teamName Название команды.
     */
    public void printTop5TallestPlayers(String teamName) {
        // Создаем объект Pageable для вывода 5 элементов
        Pageable pageable = PageRequest.of(0, 5);
        List<PlayerInfo> top5TallestPlayers = playerInfoRepository.findTop5TallestPlayersByTeam(teamName, pageable);

        if (top5TallestPlayers.isEmpty()) {
            System.out.println("No players found for the team: " + teamName);
        } else {
            System.out.println("-------------");
            System.out.println("-------------");
            System.out.println("5 самых высоких игроков из команды " + teamName + ":");
            for (PlayerInfo player : top5TallestPlayers) {
                System.out.println(player.getName() + " - Его рост: " + player.getHeight() + " inches");
            }
            System.out.println("\n"+"\n");
        }
    }

    /**
     * Находит команду с самым высоким средним возрастом, где средний рост от 74 до 78 inches
     * и средний вес от 190 до 210 lbs.
     */
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

            System.out.println("-------------");
            System.out.println("-------------");
            System.out.println("Команда с самым высоким средним возрастом, в котором средний рост от 74 до 78 inches и средний вес от 190 до 210 lbs:");
            System.out.println("Название команды: " + teamName);
            System.out.println("Средний рост команды: " + averageHeight);
            System.out.println("Средний вес команды: " + averageWeight);
            System.out.println("Средний возраст команды: " + averageAge);
            System.out.println("\n"+"\n");
        }
    }
}
