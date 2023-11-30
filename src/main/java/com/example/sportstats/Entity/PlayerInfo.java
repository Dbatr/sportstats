package com.example.sportstats.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "playerInfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "team")
    private String team;

    @Column(name = "position")
    private String position;

    // Геттеры и сеттеры
}
