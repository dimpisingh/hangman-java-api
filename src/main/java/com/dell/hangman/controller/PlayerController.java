package com.dell.hangman.controller;

import com.dell.hangman.converter.CreatePlayerConverter;
import com.dell.hangman.domain.entity.Player;
import com.dell.hangman.domain.repository.PlayerRepository;
import com.dell.hangman.dto.CreatePlayer;
import com.dell.hangman.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/hangman/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    private CreatePlayerConverter createPlayerConverter;

    @CrossOrigin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Player newPlayer(@RequestBody CreatePlayer request) {
        return playerService.newPlayer(createPlayerConverter.encode(request));
    }

}
