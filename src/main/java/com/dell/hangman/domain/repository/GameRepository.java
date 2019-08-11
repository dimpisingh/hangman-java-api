package com.dell.hangman.domain.repository;

import com.dell.hangman.domain.entity.Game;
import com.dell.hangman.domain.repository.custom.GameRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>, GameRepositoryCustom {

}
