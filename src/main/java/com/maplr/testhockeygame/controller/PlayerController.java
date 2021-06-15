package com.maplr.testhockeygame.controller;

import com.maplr.testhockeygame.dto.PlayerDto;
import com.maplr.testhockeygame.service.PlayerService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    @PutMapping("/captain/{ID}")
    public ResponseEntity<PlayerDto> makePlayerCaptain(@PathVariable("ID") long id){
        try {
            return ResponseEntity.ok(playerService.makeCaptain(id));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
