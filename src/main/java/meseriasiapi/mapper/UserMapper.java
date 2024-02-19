package meseriasiapi.mapper;

import meseriasiapi.dto.UserDto;
import meseriasiapi.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .phone(userDto.getPhone())
                .media(userDto.getMedia())
                .rating(userDto.getRating())
                .build();
    }
    public UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .phone(user.getPhone())
                .media(user.getMedia())
                .rating(user.getRating())
                .build();
    }
}