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

    // Уникальный идентификатор информации об игроке
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Название команды, к которой принадлежит игрок
    @Column(name = "team")
    private String team;

    // Позиция игрока в команде
    @Column(name = "position")
    private String position;

    // Связь с деталями игрока (его именем, ростом и др.)
    @OneToOne(mappedBy = "info")
    private PlayerDetails details;

    // Методы для получения имени, роста игрока через связанные детали
    public String getName() {
        return details.getName();
    }

    public int getHeight() {
        return details.getHeight();
    }
}
