package com.sqli.users_service.services.ImplServices;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sqli.users_service.data.entities.AccountUser;
import com.sqli.users_service.data.repositories.AccountUserRepository;


@Service
@Qualifier("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountUserRepository accountUserRepository;

    @Inject
    public UserDetailsServiceImpl(AccountUserRepository accountUserRepository) {
        this.accountUserRepository=accountUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountUser accountUser =accountUserRepository.findAccountUserByUsername(username).orElseThrow(
            ()-> new UsernameNotFoundException("invalid account")
        );
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        accountUser.getAccountRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new User(accountUser.getUsername(), accountUser.getPassword(),authorities);
    }
}
