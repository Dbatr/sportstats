package com.example.sportstats.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "player")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private PlayerDetails details = new PlayerDetails();;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "info_id", referencedColumnName = "id")
    private PlayerInfo info = new PlayerInfo();

    // Геттеры и сеттеры
    public void setName(String name) {
        this.details.setName(name);
    }
    public void setTeam(String team) {
        this.info.setTeam(team);
    }

    public void setPosition(String position) {
        this.info.setPosition(position);
    }

    public void setHeight(int height) {
        this.details.setHeight(height);
    }

    public void setWeight(int weight) {
        this.details.setWeight(weight);
    }

    public void setAge(double age) {
        this.details.setAge(age);
    }
}