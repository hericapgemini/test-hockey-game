package com.maplr.testhockeygame.service.impl;

import com.maplr.testhockeygame.dto.PlayerDto;
import com.maplr.testhockeygame.dto.TeamDto;
import com.maplr.testhockeygame.entity.Player;
import com.maplr.testhockeygame.entity.Team;
import com.maplr.testhockeygame.repository.TeamRepository;
import com.maplr.testhockeygame.service.PlayerService;
import com.maplr.testhockeygame.service.TeamService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final PlayerService playerService;
    private final TeamRepository teamRepository;

    @Override
    public TeamDto getTeamByYear(long year) throws NotFoundException {
        Optional<Team> teamOptional = teamRepository.findTeamByYear(year);
        if(teamOptional.isPresent()){
            return TeamDto.fromEntity(teamOptional.get());
        }
       throw new NotFoundException("the team that you are looking for does not exist");
    }

    @Override
    public PlayerDto addPlayerToTeam(long year, PlayerDto playerDto) throws NotFoundException {
        Optional<Team> teamOptional = teamRepository.findTeamByYear(year);
        if(teamOptional.isPresent()){
            Team team = teamOptional.get();

            Player player = playerService.savePlayer(playerDto.toEntity(team));

            team.getPlayers().add(player);

            teamRepository.save(team);

            return PlayerDto.fromEntity(player);
        }
        throw new NotFoundException("the team that you are looking for does not exist");
    }

    @Override
    public TeamDto addTeam(TeamDto teamDto) {
        Team team = teamRepository.save(teamDto.toEntity());
        teamDto.getPlayers().forEach(playerDto ->
                team.getPlayers().add(playerService.savePlayer(playerDto.toEntity(team))));
        return TeamDto.fromEntity(teamRepository.getById(teamDto.getId()));
    }

    @Override
    public List<TeamDto> getAllTeams() {
        return teamRepository.findAll().stream().map(TeamDto::fromEntity).collect(Collectors.toList());
    }
}
