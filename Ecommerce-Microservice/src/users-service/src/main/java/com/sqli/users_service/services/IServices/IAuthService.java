package com.sqli.users_service.services.IServices;

import com.sqli.users_service.data.dto.JwtRequest;

public interface IAuthService {
    String createAuthenticationToken(JwtRequest authenticationRequest) throws Exception;
}
