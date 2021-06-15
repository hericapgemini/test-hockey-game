package com.maplr.testhockeygame.controller;

import com.maplr.testhockeygame.dto.PlayerDto;
import com.maplr.testhockeygame.dto.TeamDto;
import com.maplr.testhockeygame.service.TeamService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    // ADDED FOR POSTMAN TESTS
    @GetMapping(path = "/all")
    public List<TeamDto> showAll(){
        return teamService.getAllTeams();
    }

    // ADDED FOR POSTMAN TESTS
    @PostMapping(path = "/add")
    public ResponseEntity<TeamDto> saveNewTeam(@RequestBody TeamDto teamDto){
        TeamDto result = teamService.addTeam(teamDto);
        if(result != null){
            return ResponseEntity.ok(teamDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(path = "/{year}")
    public ResponseEntity<TeamDto> getTeamParYear(@PathVariable long year){
        try {
            return ResponseEntity.ok(teamService.getTeamByYear(year));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/{year}")
    public ResponseEntity<PlayerDto> addPlayerToTeam(@PathVariable long year, @RequestBody PlayerDto playerDto) {
        try {
            return ResponseEntity.ok(teamService.addPlayerToTeam(year, playerDto));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
