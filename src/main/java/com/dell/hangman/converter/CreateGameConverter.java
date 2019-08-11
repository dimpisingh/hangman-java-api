package com.dell.hangman.converter;

import com.dell.hangman.domain.entity.Player;
import com.dell.hangman.dto.CreateGame;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CreateGameConverter {

    public static Player encode(CreateGame input) {
        return Player.builder()
                .id(input.getPlayerId())
                .build();
    }

}
