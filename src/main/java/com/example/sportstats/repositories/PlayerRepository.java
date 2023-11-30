package com.example.sportstats.repositories;

import com.example.sportstats.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
