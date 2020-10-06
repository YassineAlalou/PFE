package com.sqli.users_service.services.ImplServices;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.sqli.users_service.core.security.JwtToken;
import com.sqli.users_service.data.dto.JwtRequest;
import com.sqli.users_service.services.IServices.IAuthService;

@Service
public class authServiceImpl implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtToken jwtToken;

    @Inject
    public authServiceImpl(AuthenticationManager authenticationManager, @Qualifier("UserDetailsServiceImpl") UserDetailsService userDetailsService, JwtToken jwtToken) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtToken = jwtToken;
    }

    public String createAuthenticationToken(JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails =userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        return jwtToken.generateToken(userDetails);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
