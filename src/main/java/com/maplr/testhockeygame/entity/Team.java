package com.maplr.testhockeygame.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    private Long id;

    @Column(nullable = false)
    private String coach;

    @Column(nullable = false)
    private long year;

    @OneToMany
    private Set<Player> players = new HashSet<>();
}
