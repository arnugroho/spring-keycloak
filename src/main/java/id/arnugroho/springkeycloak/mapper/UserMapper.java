package id.arnugroho.springkeycloak.mapper;


import id.arnugroho.springkeycloak.model.dto.RegisterDto;
import id.arnugroho.springkeycloak.model.dto.UserDto;
import id.arnugroho.springkeycloak.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User fromRegisterDto(RegisterDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setNoHp(dto.getNoHp());
        user.setKtp(dto.getKtp());
        user.setAlamat(dto.getAlamat());

        return user;
    }

    public RegisterDto toRegisterDto(User user) {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail(user.getEmail());
        registerDto.setUsername(user.getUsername());
        registerDto.setNoHp(user.getNoHp());
        registerDto.setKtp(user.getKtp());
        registerDto.setAlamat(user.getAlamat());
        return registerDto;
    }

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setNoHp(user.getNoHp());
        userDto.setUserKeyId(user.getUserKeyId());
        userDto.setKtp(user.getKtp());
        userDto.setAlamat(user.getAlamat());
        return userDto;
    }
}
