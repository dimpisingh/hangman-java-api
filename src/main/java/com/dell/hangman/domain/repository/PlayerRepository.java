package com.dell.hangman.domain.repository;

import com.dell.hangman.domain.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    public Player findByUsername(String username);

}
