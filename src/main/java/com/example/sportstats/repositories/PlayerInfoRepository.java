package com.example.sportstats.repositories;

import com.example.sportstats.Entity.PlayerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerInfoRepository extends JpaRepository<PlayerInfo, Long> {

}
