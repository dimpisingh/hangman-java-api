package com.dell.hangman.service;

import com.dell.hangman.domain.entity.Game;
import com.dell.hangman.domain.entity.Player;
import com.dell.hangman.domain.repository.GameRepository;
import com.dell.hangman.domain.repository.PlayerRepository;
import com.dell.hangman.domain.repository.custom.GameRepositoryCustomImpl;
import com.dell.hangman.dto.Guess;
import com.dell.hangman.exception.BadRequestException;
import com.dell.hangman.wordBank.DOMParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameRepositoryCustomImpl gameRepositoryCustom;

    @Autowired
    private PlayerRepository playerRepository;

    public Game newGame(Player requestPlayer) throws BadRequestException {

        Optional<Player> findPlayer = playerRepository.findById(requestPlayer.getId());

        if (!findPlayer.isPresent()) {
            throw new BadRequestException("Player not found!");
        }

        Player player = findPlayer.get();
        List<Game> playerGameHistory = gameRepositoryCustom.findGamesByPlayerId(player.getId());


        List words = DOMParser.read();

        for (int i = 0; i < playerGameHistory.size(); i++) {
            String targetWord = playerGameHistory.get(i).getTargetWord();
            if (words.indexOf(targetWord) > -1) {
                words.remove(targetWord);
            }
        }

        if (words.size() == 0) {
            throw new BadRequestException("All words were played... Reset or leave forever!!");
        }


        int random = new Random().nextInt(words.size());
        String randomWord = (String) words.get(random);

        Game game = new Game();
        game.setPlayerId(player.getId());
        game.setTargetWord(randomWord);

        return gameRepository.save(game);

    }

    public Game guess(Long gameId, Guess guess) throws BadRequestException {

        Optional<Game> findGame = gameRepository.findById(gameId);

        if (!findGame.isPresent()) {
            throw new BadRequestException("Game not found!");
        }

        Game game = findGame.get();

        if (game.isGameOver()) {
            throw new BadRequestException("Game already over!");
        }

        String targetWord = game.getTargetWord();
        Character guessCharacter = guess.getCharacter();
        List guesses  = game.getGuesses();

        if (guesses.indexOf(guessCharacter) > -1) {
            throw new BadRequestException("This character was already guessed!");
        }

        guesses.add(guessCharacter);
        game.setGuesses(guesses);

        if (targetWord.indexOf(guessCharacter) < 0) {
            game.setGuessesLeft(game.getGuessesLeft() - 1);

            if (game.getGuessesLeft() < 1) {
                game.setGameOver(true);
            }
        } else {

            List targetWordChars = targetWord.chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toList());

            if (guesses.containsAll(targetWordChars)) {
                game.setGameOver(true);
                game.setGameWon(true);
            }

        }

        return gameRepository.save(game);
     }

}
