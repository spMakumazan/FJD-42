package ru.netology.fjd42.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tokens")
public class Token {

    @Id
    private String authToken;

    @OneToOne
    @JoinColumn(nullable = false)
    private User user;
}
