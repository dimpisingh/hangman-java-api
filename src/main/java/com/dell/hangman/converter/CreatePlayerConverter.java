package com.dell.hangman.converter;

import com.dell.hangman.domain.entity.Player;
import com.dell.hangman.dto.CreatePlayer;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CreatePlayerConverter {

    public static Player encode(CreatePlayer input) {
        return Player.builder()
                .username(input.getUsername())
                .build();
    }

}
