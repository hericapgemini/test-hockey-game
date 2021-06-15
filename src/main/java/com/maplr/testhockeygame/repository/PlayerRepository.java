package com.maplr.testhockeygame.repository;

import com.maplr.testhockeygame.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @RepositoryRestResource(exported = false) to avoid creating Repository endpoints in prod
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
