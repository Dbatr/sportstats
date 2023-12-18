package com.example.sportstats.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "playerDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDetails {

    // Уникальный идентификатор игрока
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Имя игрока
    @Column(name = "name")
    private String name;

    // Рост игрока в дюймах
    @Column(name = "height")
    private int height;

    // Вес игрока в фунтах
    @Column(name = "weight")
    private int weight;

    // Возраст игрока
    @Column(name = "age")
    private double age;

    // Связь с информацией о игроке (командой и позицией)
    @OneToOne
    @JoinColumn(name = "info_id")
    private PlayerInfo info;
}
