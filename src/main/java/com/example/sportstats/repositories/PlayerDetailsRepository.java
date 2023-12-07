package com.example.sportstats.repositories;

import com.example.sportstats.Entity.PlayerDetails;
import com.example.sportstats.Entity.PlayerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerDetailsRepository extends JpaRepository<PlayerDetails, Long> {
}
