package com.maplr.testhockeygame.repository;

import com.maplr.testhockeygame.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// @RepositoryRestResource(exported = false) to avoid creating Repository endpoints in prod
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findTeamByYear(long year);
}
