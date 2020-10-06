package com.sqli.users_service.data.repositories;

import java.util.Optional;

import com.sqli.users_service.data.entities.AccountRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, Long> {
    Optional<AccountRole> findAccountRoleByName(String name);
    public Boolean existsAccountRolesByName(String roleName);
}
