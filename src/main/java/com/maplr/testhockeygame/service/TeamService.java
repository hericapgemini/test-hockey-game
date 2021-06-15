package com.maplr.testhockeygame.service;

import com.maplr.testhockeygame.dto.PlayerDto;
import com.maplr.testhockeygame.dto.TeamDto;
import javassist.NotFoundException;

import java.util.List;

public interface TeamService {
    TeamDto getTeamByYear(long year) throws NotFoundException;
    PlayerDto addPlayerToTeam(long year, PlayerDto playerDto) throws NotFoundException;

    // added to insert data for tests
    TeamDto addTeam(TeamDto teamDto);
    List<TeamDto> getAllTeams();
}
