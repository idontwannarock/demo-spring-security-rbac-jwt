package com.example.springsecurityrbacjwt.persistence.repository;

import com.example.springsecurityrbacjwt.persistence.domain.Privileges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegesRepository extends JpaRepository<Privileges, Long> {
}
