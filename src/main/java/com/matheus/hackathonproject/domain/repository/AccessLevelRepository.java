package com.matheus.hackathonproject.domain.repository;

import com.matheus.hackathonproject.domain.model.AccessLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLevelRepository extends CustomJpaRepository<AccessLevel, Long> {
}
