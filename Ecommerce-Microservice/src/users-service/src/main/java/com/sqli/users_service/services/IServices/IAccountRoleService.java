package com.sqli.users_service.services.IServices;

import com.sqli.users_service.core.exceptions.DataAlreadyUsedException;
import com.sqli.users_service.core.exceptions.NoDataFoundException;
import com.sqli.users_service.data.entities.AccountRole;

import java.util.List;

public interface IAccountRoleService {
    AccountRole getRoleByName(String name) throws NoDataFoundException;

    AccountRole getRoleById(Long id) throws NoDataFoundException;

    List<AccountRole> getAllRoles() throws NoDataFoundException;

    AccountRole addAccountRole(AccountRole accountRole) throws DataAlreadyUsedException;

    AccountRole updateRole(long idRole, AccountRole role) throws NoDataFoundException;

    void deleteRole(Long idRole) throws NoDataFoundException;
}
