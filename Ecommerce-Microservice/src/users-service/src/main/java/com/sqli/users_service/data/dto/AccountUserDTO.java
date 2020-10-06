package com.sqli.users_service.data.dto;

import java.util.Collection;
import java.util.List;

import com.sqli.users_service.data.entities.AccountRole;
import com.sqli.users_service.data.entities.AccountUser;

import lombok.Data;

@Data
public class AccountUserDTO {
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private Collection<AccountRole> accountRoles;
    public AccountUserDTO() {
    }

    public AccountUserDTO(AccountUser accountUser) {
        this.userId = accountUser.getId();
        this.username = accountUser.getUsername();
        this.firstName = accountUser.getFirstName();
        this.lastName = accountUser.getLastName();
        this.address = accountUser.getAddress();
        this.email = accountUser.getEmail();
        this.phone= accountUser.getPhone();
        this.accountRoles =accountUser.getAccountRoles();
    }

}

