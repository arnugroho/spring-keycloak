package id.arnugroho.springkeycloak.service;

import id.arnugroho.springkeycloak.model.dto.RegisterDto;
import id.arnugroho.springkeycloak.model.dto.UserDto;

public interface UserService {
    UserDto findByUsername(String username);
    public RegisterDto saveRegister(RegisterDto registerDto);
}
