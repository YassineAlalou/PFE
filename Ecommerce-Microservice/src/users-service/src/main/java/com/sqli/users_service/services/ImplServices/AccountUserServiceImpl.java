package com.sqli.users_service.services.ImplServices;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.sqli.users_service.core.exceptions.DataAlreadyUsedException;
import com.sqli.users_service.core.exceptions.NoDataFoundException;
import com.sqli.users_service.core.exceptions.ValidationException;
import com.sqli.users_service.data.dto.AccountUserDTO;
import com.sqli.users_service.data.dto.AccountUserRequestDTO;
import com.sqli.users_service.data.entities.AccountRole;
import com.sqli.users_service.data.entities.AccountUser;
import com.sqli.users_service.data.repositories.AccountRoleRepository;
import com.sqli.users_service.data.repositories.AccountUserRepository;
import com.sqli.users_service.services.IServices.IAccountUserService;

@Service
@Transactional
@CrossOrigin("*")
public class AccountUserServiceImpl implements IAccountUserService {

    private final AccountUserRepository accountUserRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public AccountUserServiceImpl(AccountUserRepository accountUserRepository,
                                  AccountRoleRepository accountRoleRepository,
                                  BCryptPasswordEncoder passwordEncoder) {
        this.accountUserRepository = accountUserRepository;
        this.accountRoleRepository = accountRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AccountUserDTO saveUser(AccountUserRequestDTO accountUserRequest) throws DataAlreadyUsedException,ValidationException{
        AccountUser accountUser=accountUserRequest.getAccountUser();

        if (accountUserRepository.findAccountUserByUsername(accountUser.getUsername()).isPresent()) {
            throw new DataAlreadyUsedException("The username already in use");
        }
        if (!accountUser.getPassword().equals(accountUserRequest.getConfirmedPassword())) {
            throw new ValidationException("Please confirm your password correctly!");
        }
        accountUser.setPassword(passwordEncoder.encode(accountUser.getPassword()));
        this.addRoleToAccountUser(accountUser,accountUserRequest.getIdRole());
        return new AccountUserDTO(accountUserRepository.save(accountUser));
    }

    @Override
    public AccountUserDTO getUserByUsername(String username) throws NoDataFoundException {
        return  accountUserRepository.findAccountUserByUsername(username).map(AccountUserDTO::new).orElseThrow(
            ()-> new NoDataFoundException("No accountUser identified by username: " + username));
    }

    @Override
    public AccountUserDTO getUserById(Long userId) throws NoDataFoundException {
        return accountUserRepository.findById(userId).map(AccountUserDTO::new).orElseThrow(
            ()-> new NoDataFoundException("No accountUser identified by : " + userId));
    }

    @Override
    public AccountUserDTO updateUser(Long userId, AccountUser newAccountUser) throws NoDataFoundException {
        if(!accountUserRepository.findById(userId).isPresent())
            throw new NoDataFoundException("No AccountUser was identified by " + userId);
         newAccountUser.setId(userId);
        return new AccountUserDTO(accountUserRepository.save(newAccountUser));
    }

    @Override
    public List<AccountUserDTO> getAllAccounts() throws NoDataFoundException {
        return accountUserRepository.findAll().stream().map(
            AccountUserDTO::new
        ).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) throws NoDataFoundException {
        Optional<AccountUser> accountUser = accountUserRepository.findById(userId);
        accountUserRepository.delete(
            accountUser.orElseThrow(
                () -> new NoDataFoundException("No accountUser identified by : " + userId)));
    }

    @Override
    public AccountUserDTO addRoleToUser(Long userId,Long idRole) {
        AccountUser accountUser = accountUserRepository.findById(userId).orElseThrow(
            () -> new NoDataFoundException("No accountUser identified by : " + userId)
        );
        this.addRoleToAccountUser(accountUser, idRole);
        return new AccountUserDTO(accountUserRepository.save(accountUser));
    }

    private void addRoleToAccountUser(AccountUser accountUser, Long idRole) {
        AccountRole accountRole = accountRoleRepository.findById(idRole).orElseThrow(
            ()->  new NoDataFoundException("No AccountRole was identified by " +idRole));
        accountUser.addNewRole(accountRole);
    }

}
