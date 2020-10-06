package com.sqli.users_service.data.dto;

import com.sqli.users_service.data.entities.AccountUser;

import lombok.Data;

@Data
public class AccountUserRequestDTO {

    private final AccountUser accountUser;
    private final String confirmedPassword;
    private final Long idRole;

    public AccountUserRequestDTO(AccountUser accountUser, String confirmedPassword, Long idRole) {
        this.accountUser = accountUser;
        this.confirmedPassword = confirmedPassword;
        this.idRole = idRole;
    }

}
