package com.dell.hangman.domain.repository.custom;

import com.dell.hangman.domain.entity.Game;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepositoryCustom {

    List<Game> findGamesByPlayerId(Long playerId);

}
