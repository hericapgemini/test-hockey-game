package com.maplr.testhockeygame.service.impl;

import com.maplr.testhockeygame.dto.PlayerDto;
import com.maplr.testhockeygame.entity.Player;
import com.maplr.testhockeygame.entity.Team;
import com.maplr.testhockeygame.repository.PlayerRepository;
import com.maplr.testhockeygame.service.PlayerService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public PlayerDto makeCaptain(long id) throws NotFoundException {
        Optional<Player> playerOptional = playerRepository.findById(id);
        if(playerOptional.isPresent()){
            Player player = playerOptional.get();
            this.removeTheOldCaptain(player.getTeam());
            player.setCaptain(true);
            Player savedPlayer = playerRepository.save(player);
            return PlayerDto.fromEntity(savedPlayer);
        }
        throw new NotFoundException("the player that you are looking for does not exist");
    }

    @Override
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    private void removeTheOldCaptain(Team team){
        team.getPlayers()
                .stream()
                .filter(Player::isCaptain)
                .forEach(player -> {
                    player.setCaptain(false);
                    playerRepository.save(player);
                });
    }
}
