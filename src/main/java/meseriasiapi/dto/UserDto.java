package meseriasiapi.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UserDto {

    private UUID id;

    private String email;

    private String password;

    private String accountType;

    private String phone;

    private String media;

    private String rating;

}
