package com.sqli.users_service.controller;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sqli.users_service.data.dto.JwtRequest;
import com.sqli.users_service.data.dto.JwtResponse;
import com.sqli.users_service.services.IServices.IAuthService;

@RestController
@CrossOrigin
public class AuthController {

    private final IAuthService authService;

    @Inject
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        String token=authService.createAuthenticationToken(authenticationRequest);
        return ResponseEntity.ok(new JwtResponse(token));
    }

}
