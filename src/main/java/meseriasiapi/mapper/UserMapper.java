package meseriasiapi.mapper;

import lombok.AllArgsConstructor;
import meseriasiapi.domain.User;
import meseriasiapi.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

    public User toEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .phone(userDto.getPhone())
                .rating(userDto.getRating())
                .creationDate(userDto.getCreationDate())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .description(userDto.getDescription())
                .yearsOfExperience(userDto.getYearsOfExperience())
                .build();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .phone(user.getPhone())
                .rating(user.getRating())
                .creationDate(user.getCreationDate())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .description(user.getDescription())
                .yearsOfExperience(user.getYearsOfExperience())
                .build();
    }
}