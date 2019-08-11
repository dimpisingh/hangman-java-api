package com.dell.hangman.service;

import com.dell.hangman.domain.entity.Player;
import com.dell.hangman.domain.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player newPlayer(Player createPlayer) {

        Player player = new Player();
        player.setUsername(createPlayer.getUsername());

        return playerRepository.save(player);

    }

}
