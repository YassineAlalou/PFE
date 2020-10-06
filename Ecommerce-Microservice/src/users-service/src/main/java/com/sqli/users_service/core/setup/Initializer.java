package com.sqli.users_service.core.setup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.sqli.users_service.core.exceptions.DataAlreadyUsedException;
import com.sqli.users_service.core.exceptions.NoDataFoundException;
import com.sqli.users_service.data.entities.AccountRole;
import com.sqli.users_service.data.entities.AccountUser;
import com.sqli.users_service.data.repositories.AccountRoleRepository;
import com.sqli.users_service.data.repositories.AccountUserRepository;

@Component
public class Initializer {
    private static AccountUserRepository accountUserRepository;
    private static AccountRoleRepository accountRoleRepository;

    private static BCryptPasswordEncoder passwordEncoder;

    public Initializer(AccountUserRepository accountUserRepository, AccountRoleRepository accountRoleRepository, BCryptPasswordEncoder passwordEncoder) {
        Initializer.accountUserRepository = accountUserRepository;
        Initializer.accountRoleRepository = accountRoleRepository;
        Initializer.passwordEncoder = passwordEncoder;
    }

    public static void initData() throws DataAlreadyUsedException, NoDataFoundException {
        AccountRole role1 = new AccountRole("ADMIN");
        AccountRole role2 = new AccountRole("AGENT");
        AccountRole role3 = new AccountRole("CUSTOMER");
        AccountRole role4 = new AccountRole("DELIVERY");

        List<AccountRole> roles1 = new ArrayList<>();
        roles1.add(role1);
        List<AccountRole> roles2 = new ArrayList<>();
        roles2.add(role2);
        List<AccountRole> roles3 = new ArrayList<>();
        roles3.add(role3);
        List<AccountRole> roles4 = new ArrayList<>();
        roles4.add(role4);
        accountRoleRepository.save(role1);
        accountRoleRepository.save(role2);
        accountRoleRepository.save(role3);
        accountRoleRepository.save(role4);
        AccountUser acc1 =AccountUser.builder()
            .username("admin")
            .firstName("lhou")
            .lastName("ouarhou")
            .password(passwordEncoder.encode("admin"))
            .address("address test")
            .email("lhou@gmail.com")
            .phone("+212603050509")
            .accountRoles(roles1)
            .build();
        AccountUser acc2 =AccountUser.builder()
            .username("agent")
            .firstName("lhou")
            .lastName("ouarhou")
            .password(passwordEncoder.encode("agent"))
            .address("address test")
            .email("test@gmail.com")
            .phone("+212615890509")
            .accountRoles(roles2)
            .build();
        AccountUser acc3 =AccountUser.builder()
            .username("customer")
            .firstName("youssef")
            .lastName("ouarhou")
            .password(passwordEncoder.encode("customer"))
            .address("address test")
            .email("customer@gmail.com")
            .phone("+212603125909")
            .accountRoles(roles3)
            .build();
        AccountUser acc4 =AccountUser.builder()
            .username("delivery")
            .firstName("abdlaziz")
            .lastName("ouarhou")
            .password(passwordEncoder.encode("delivery"))
            .address("address test")
            .email("delivery@gmail.com")
            .phone("+212603125959")
            .accountRoles(roles4)
            .build();
        accountUserRepository.save(acc1);
        accountUserRepository.save(acc2);
        accountUserRepository.save(acc3);
        accountUserRepository.save(acc4);

    }
}
