package com.maplr.testhockeygame.service;

import com.maplr.testhockeygame.dto.PlayerDto;
import com.maplr.testhockeygame.entity.Player;
import javassist.NotFoundException;

public interface PlayerService {
    PlayerDto makeCaptain(long id) throws NotFoundException;
    Player savePlayer(Player player);
}
