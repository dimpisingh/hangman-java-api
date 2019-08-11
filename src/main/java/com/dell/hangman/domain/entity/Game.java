package com.dell.hangman.domain.entity;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long playerId;

    @NotNull
    @Column(name = "target_word", nullable = false)
    private String targetWord = "";

    @NotNull
    @Column(name = "guesses", nullable = false)
    @ElementCollection
    private List<Character> guesses = new ArrayList<Character>();

    @NotNull
    @Column(name = "guessesLeft", nullable = false)
    private int guessesLeft = 6;

    @NotNull
    @Column(name = "game_over", nullable = false)
    private boolean gameOver = false;

    @NotNull
    @Column(name = "game_won", nullable = false)
    private boolean gameWon = false;

}
