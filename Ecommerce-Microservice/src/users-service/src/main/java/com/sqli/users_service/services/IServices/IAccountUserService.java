package com.sqli.users_service.services.IServices;

import com.sqli.users_service.core.exceptions.DataAlreadyUsedException;
import com.sqli.users_service.core.exceptions.NoDataFoundException;
import com.sqli.users_service.core.exceptions.ValidationException;
import com.sqli.users_service.data.dto.AccountUserDTO;
import com.sqli.users_service.data.dto.AccountUserRequestDTO;
import com.sqli.users_service.data.entities.AccountUser;

import java.util.List;

public interface IAccountUserService {
    AccountUserDTO saveUser(AccountUserRequestDTO accountUserRequest) throws DataAlreadyUsedException, ValidationException;
    AccountUserDTO getUserByUsername(String username) throws NoDataFoundException;
    AccountUserDTO getUserById(Long userId) throws NoDataFoundException;
    AccountUserDTO updateUser(Long userId, AccountUser newAccountUser) throws NoDataFoundException;
    List<AccountUserDTO> getAllAccounts() throws NoDataFoundException;
    void deleteUser(Long userId) throws NoDataFoundException;
    AccountUserDTO addRoleToUser(Long userId,Long idRole) throws NoDataFoundException;
}
