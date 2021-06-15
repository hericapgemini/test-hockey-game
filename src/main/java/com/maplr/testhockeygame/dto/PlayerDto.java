package com.maplr.testhockeygame.dto;

import com.maplr.testhockeygame.entity.Player;
import com.maplr.testhockeygame.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    private long number;
    private String name;
    private String lastname;
    private boolean isCaptain;

    public Player toEntity(Team team){
        return new Player(null, this.number, this.name, this.lastname, this.isCaptain, team);
    }

    public static PlayerDto fromEntity(Player player){
        return new PlayerDto(player.getNumber(), player.getName(), player.getLastname(), player.isCaptain());
    }

}
