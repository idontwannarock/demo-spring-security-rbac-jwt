package com.example.springsecurityrbacjwt.persistence.repository;

import com.example.springsecurityrbacjwt.persistence.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

    Roles findByName(String name);
}
