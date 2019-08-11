package com.dell.hangman.dto;

import com.dell.hangman.domain.entity.Game;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerGameHistory {

    private List<Game> gameList;

}
