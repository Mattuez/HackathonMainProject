package com.matheus.hackathonproject.domain.repository;

import com.matheus.hackathonproject.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CustomJpaRepository<Permission, Long> {
}
