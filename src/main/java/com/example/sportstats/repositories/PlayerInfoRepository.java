package com.example.sportstats.repositories;

import com.example.sportstats.Entity.PlayerDetails;
import com.example.sportstats.Entity.PlayerInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerInfoRepository extends JpaRepository<PlayerInfo, Long> {

    @Query("SELECT p.team, AVG(d.height)" +
            " FROM PlayerInfo p JOIN p.details d" +
            " GROUP BY p.team " +
            "ORDER BY AVG(d.height) DESC")
    List<Object[]> findTeamAverageHeight();


    @Query("SELECT p FROM PlayerInfo p JOIN FETCH p.details d WHERE p.team = :teamName ORDER BY d.height DESC")
    List<PlayerInfo> findTop5TallestPlayersByTeam(String teamName, Pageable pageable);
}
