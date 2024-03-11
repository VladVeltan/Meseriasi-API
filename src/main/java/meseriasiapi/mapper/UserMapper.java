package meseriasiapi.mapper;

import lombok.AllArgsConstructor;
import meseriasiapi.domain.User;
import meseriasiapi.dto.UserDto;
import meseriasiapi.service.MediaService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
    private final MediaService mediaService;
    public User toEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .phone(userDto.getPhone())
                .media(mediaService.findById(userDto.getMedia_id()))
                .rating(userDto.getRating())
                .build();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .phone(user.getPhone())
                .media_id(user.getMedia().getId())
                .rating(user.getRating())
                .build();
    }
}