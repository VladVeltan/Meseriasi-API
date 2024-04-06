package meseriasiapi.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import meseriasiapi.domain.User;
import meseriasiapi.dto.UserDto;
import meseriasiapi.mapper.UserMapper;
import meseriasiapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> usersList = userService.getAllUsers();
        List<UserDto> userDtoList = usersList.stream().map(userMapper::toDto).toList();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID userId) {
        try {
            User user = userService.findById(userId);
            UserDto userDto = userMapper.toDto(user);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody @NonNull UserDto userDto) {
        return new ResponseEntity<>(userMapper.toDto(userService.createUser(userMapper.toEntity(userDto))), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<UserDto> updateUser(@RequestBody @NonNull UserDto userDto) {
        return new ResponseEntity<>(userMapper.toDto(userService.updateUser(userMapper.toEntity(userDto))), HttpStatus.OK);
    }
}
