package com.example.sportstats.repositories;

import com.example.sportstats.Entity.PlayerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerDetailsRepository extends JpaRepository<PlayerDetails, Long> {

}
