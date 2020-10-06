package com.sqli.users_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sqli.users_service.core.exceptions.DataAlreadyUsedException;
import com.sqli.users_service.core.exceptions.NoDataFoundException;
import com.sqli.users_service.data.dto.AccountUserDTO;
import com.sqli.users_service.data.dto.AccountUserRequestDTO;
import com.sqli.users_service.data.entities.AccountUser;
import com.sqli.users_service.services.IServices.IAccountUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(path = "/users")
@Api(value = "AccountUser API")
public class AccountUserController {
    private final IAccountUserService accountUserService;

    public AccountUserController(IAccountUserService accountUserService) {
        this.accountUserService = accountUserService;
    }

    @GetMapping(path = "")
    @ApiOperation(value = "Get All AccountUsers")
    public List<AccountUserDTO> getAllAccountUsers() throws NoDataFoundException {
        return accountUserService.getAllAccounts();
    }


    @GetMapping(path = "/{userId}")
    @ApiOperation(value = "get AccountUser by userId")
    public AccountUserDTO getAccountUserById(@PathVariable("userId") Long userId) throws NoDataFoundException {
        return accountUserService.getUserById(userId);
    }

    @GetMapping(path = "/search/")
    @ApiOperation(value = "get AccountUser by username")
    public AccountUserDTO getAccountUserByUsername(@RequestParam String username) throws NoDataFoundException {
        return accountUserService.getUserByUsername(username);
    }

    @PostMapping(path = "")
    @ApiOperation(value = "add new AccountUser")
    public AccountUserDTO addAccountUser(@RequestBody AccountUserRequestDTO accountUserRequest) throws NoDataFoundException, DataAlreadyUsedException {
        return accountUserService.saveUser(accountUserRequest);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/{userId}")
    @ApiOperation(value = "Update AccountUser using the id.")
    public AccountUserDTO updateAccountUser(@PathVariable Long userId, @RequestBody AccountUser accountUser) throws NoDataFoundException {
        return accountUserService.updateUser(userId, accountUser);
    }

    @DeleteMapping(path = "/{userId}")
    @ApiOperation(value = "Delete AccountUser using the id.")
    public ResponseEntity<Void> deleteAccountUser(@PathVariable Long userId) throws NoDataFoundException {
        accountUserService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{userId}/roles")
    @ApiOperation(value = "Add new AccountRole to AccountUser using the userId.")
    public AccountUserDTO addRoleToUser(@PathVariable("userId") Long userId,@RequestParam("idRole") Long idRole) {
        return accountUserService.addRoleToUser(userId,idRole);
    }

}
