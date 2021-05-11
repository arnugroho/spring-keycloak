package id.arnugroho.springkeycloak.controller;
import id.arnugroho.springkeycloak.model.DefaultResponse;
import id.arnugroho.springkeycloak.model.dto.RegisterDto;
import id.arnugroho.springkeycloak.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public DefaultResponse create(@RequestBody @Valid RegisterDto registerDto) {
        return DefaultResponse.ok(userService.saveRegister(registerDto));
    }
}
