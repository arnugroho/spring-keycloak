package id.arnugroho.springkeycloak.controller;

import id.arnugroho.springkeycloak.model.DefaultResponse;
import id.arnugroho.springkeycloak.model.dto.keycloak.TokenResponseDto;
import id.arnugroho.springkeycloak.model.dto.request.LoginRequestDto;
import id.arnugroho.springkeycloak.webclient.KeyCloakWebClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;


@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping(path = "/anonym")
    public DefaultResponse getToken(){

        return DefaultResponse.ok("Gak Perlu token");
    }

    @GetMapping(path = "/role")
    @RolesAllowed("role1")
    public DefaultResponse getData(){

        return DefaultResponse.ok("Perlu Token");
    }

}
