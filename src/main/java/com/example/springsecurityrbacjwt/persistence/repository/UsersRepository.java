package com.example.springsecurityrbacjwt.persistence.repository;

import com.example.springsecurityrbacjwt.persistence.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByUsernameAndPassword(String username, String encodedPassword);
}
