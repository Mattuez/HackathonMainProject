package com.matheus.hackathonproject.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(
        name = "_user"
)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "email",
            unique = true,
            nullable = false
    )
    private String email;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_access_level",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    foreignKey = @ForeignKey(
                            name = "fk_user_access_level_user"
                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "access_level_id",
                    foreignKey = @ForeignKey(
                            name = "fk_user_access_level_access_level"
                    )
            )
    )
    private Set<AccessLevel> accessLevels = new HashSet<>();

    public void addAccessLevel(AccessLevel accessLevel) {
        this.accessLevels.add(accessLevel);
    }

    public void removeAccessLevel(AccessLevel accessLevel) {
        this.accessLevels.remove(accessLevel);
    }
}
