package com.matheus.hackathonproject.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "access_level")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AccessLevel {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @EqualsAndHashCode.Include
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @ManyToMany
    @JoinTable(
            name = "access_level_permission",
            joinColumns = @JoinColumn(
                    name = "access_level_id",
                    foreignKey = @ForeignKey(
                            name = "fk_access_level_permission_access_level"
                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "permission_id",
                    foreignKey = @ForeignKey(
                            name = "fk_access_level_permission_permission"
                    )
            )
    )
    private Set<Permission> permissions = new HashSet<>();

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
    }

    public void removePermission(Permission permission) {
        this.permissions.remove(permission);
    }
}

