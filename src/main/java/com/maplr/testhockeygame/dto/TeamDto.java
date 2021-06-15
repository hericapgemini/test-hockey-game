package com.maplr.testhockeygame.dto;

import com.maplr.testhockeygame.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {
    private long id;
    private String coach;
    private long year;
    private List<PlayerDto> players;

    public Team toEntity(){
        return new Team(this.id, this.coach, this.year, new HashSet<>());
    }

    public static TeamDto fromEntity(Team team){
        return new TeamDto(
                team.getId(),
                team.getCoach(),
                team.getYear(),
                team.getPlayers().stream().map(PlayerDto::fromEntity).collect(Collectors.toList())
        );
    }
}
