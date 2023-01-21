package com.example.showmeyourability.users.infrastructure.repository;


import com.example.showmeyourability.users.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
