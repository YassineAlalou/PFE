package com.sqli.users_service.data.repositories;

import java.util.Optional;

import com.sqli.users_service.data.entities.AccountUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountUserRepository extends JpaRepository<AccountUser, Long> {
   public Optional<AccountUser> findAccountUserByUsername(String username);
   public Optional<AccountUser> findAccountUserByEmail(String email);
   public Boolean existsByUsername(String username);
}
