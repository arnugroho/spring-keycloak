package id.arnugroho.springkeycloak.model.dto;

import lombok.Data;

@Data
public class UserDto extends CommonDto<Long> {
    private String email;
    private String username;
    private String noHp;
    private String userKeyId;
    private String ktp;
    private String alamat;
}
