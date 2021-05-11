package id.arnugroho.springkeycloak.service.impl;

import id.arnugroho.springkeycloak.configuration.exception.CommonException;
import id.arnugroho.springkeycloak.mapper.UserMapper;
import id.arnugroho.springkeycloak.model.dto.RegisterDto;
import id.arnugroho.springkeycloak.model.dto.UserDto;
import id.arnugroho.springkeycloak.model.entity.User;
import id.arnugroho.springkeycloak.repository.UserRepository;
import id.arnugroho.springkeycloak.service.UserService;
import id.arnugroho.springkeycloak.webclient.KeyCloakWebClient;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KeyCloakWebClient keyCloakWebClient;

    public UserServiceImpl(UserRepository repository, UserMapper userMapper, KeyCloakWebClient keyCloakWebClient) {
        this.userRepository = repository;
        this.userMapper = userMapper;
        this.keyCloakWebClient = keyCloakWebClient;
    }

    @Override
    public UserDto findByUsername(String username) {
        return userMapper.toUserDto(userRepository.findByUsername(username).get());
    }

    @Override
    public RegisterDto saveRegister(RegisterDto registerDto) {
        /* Ada Kemungkinan terdaftar di keycloak tapi gagal simpen ke table biodata
         * abaikan dulu*/
        //lower case email
        registerDto.setEmail(registerDto.getEmail().toLowerCase());
        User user = userMapper.fromRegisterDto(registerDto);
//        validateRegister(biodata);
        user.setCreatedBy(registerDto.getEmail());
        user.setCreatedOn(new Date());

        Response response = keyCloakWebClient.doRegister(registerDto);
        if (response.getStatus() == HttpStatus.CREATED.value()) {
            try {
                String userId = CreatedResponseUtil.getCreatedId(response);
                user.setUserKeyId(userId);
                registerDto = userMapper.toRegisterDto(userRepository.save(user));
                // sendSuccessRegistrationMail(biodata.getEmail(), biodata.getNama());
                return registerDto;
            } catch (Exception e) {
                keyCloakWebClient.deleteUser(response);
                throw new RuntimeException(e);
            }
        } else {
            throw new CommonException("Gagal Register");

        }
    }

}
