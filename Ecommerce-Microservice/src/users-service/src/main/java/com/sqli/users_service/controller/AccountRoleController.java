package com.sqli.users_service.controller;

import com.sqli.users_service.core.exceptions.DataAlreadyUsedException;
import com.sqli.users_service.core.exceptions.NoDataFoundException;
import com.sqli.users_service.data.entities.AccountRole;
import com.sqli.users_service.services.IServices.IAccountRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/roles")
@Api(value = "AccountRole resource API")
public class AccountRoleController {
    private final IAccountRoleService accountRoleService;

    public AccountRoleController(IAccountRoleService accountRoleService) {
        this.accountRoleService = accountRoleService;
    }

    @PostMapping(path = "")
    @ApiOperation(value = "Add new AccountRole")
    public AccountRole addAccountRole(@RequestBody AccountRole accountRole) throws DataAlreadyUsedException{
        return accountRoleService.addAccountRole(accountRole);
    }

    @GetMapping(path = "")
    @ApiOperation(value = "Get All AccountRoles")
    public List<AccountRole> getAllRoles() throws NoDataFoundException {
        return accountRoleService.getAllRoles();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Get AccountRole using the id.")
    public AccountRole getAccountRoleById(@PathVariable Long id) throws NoDataFoundException {
        return accountRoleService.getRoleById(id);
    }

    @GetMapping(path = "/search")
    @ApiOperation(value = "Get AccountRole using the RoleName")
    public AccountRole getAccountRoleByName(@RequestParam("name") String name) throws NoDataFoundException {
        return accountRoleService.getRoleByName(name);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Update AccountRole using the id.")
    public AccountRole updateAccountRole(@PathVariable long idRole, @RequestBody AccountRole role) throws NoDataFoundException {
        return accountRoleService.updateRole(idRole, role);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete AccountRole using the id.")
    public ResponseEntity<Void> deleteAccountRole(@PathVariable Long id) throws NoDataFoundException {
        accountRoleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
