package com.dell.hangman.domain.repository.custom;

import com.dell.hangman.domain.GenericQueryDslSupport;
import com.dell.hangman.domain.entity.Game;
import com.dell.hangman.domain.entity.QGame;

import java.util.List;

public class GameRepositoryCustomImpl extends GenericQueryDslSupport<Game> implements GameRepositoryCustom {

    private static final QGame qGame = QGame.game;

    @Override
    public List<Game> findGamesByPlayerId(Long playerId) {
        return (List<Game>) from(qGame).where(qGame.playerId.eq(playerId)).fetch();
    }

}
