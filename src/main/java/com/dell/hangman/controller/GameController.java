package com.dell.hangman.controller;

import com.dell.hangman.converter.CreateGameConverter;
import com.dell.hangman.dto.CreateGame;
import com.dell.hangman.dto.Guess;
import com.dell.hangman.domain.entity.Game;
import com.dell.hangman.service.GameService;
import com.dell.hangman.exception.BadRequestException;
import com.dell.hangman.domain.repository.GameRepository;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin
@RestController
@RequestMapping("/hangman/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameRepository gameRepository;

    private CreateGameConverter createGameConverter;

    @CrossOrigin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Game newGame(@RequestBody CreateGame request) throws BadRequestException {
        return gameService.newGame(CreateGameConverter.encode(request));
    }

    @CrossOrigin
    @PutMapping(value = "/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Game guess(@PathVariable("gameId") Long gameId, @RequestBody Guess guess) throws BadRequestException {
        return gameService.guess(gameId, guess);
    }

}
