package meseriasiapi.controller;

import lombok.RequiredArgsConstructor;
import meseriasiapi.domain.User;
import meseriasiapi.dto.UserDto;
import meseriasiapi.mapper.UserMapper;
import meseriasiapi.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<User> usersList=userService.getAllUsers();
        List<UserDto> userDtoList=usersList.stream().map(userMapper::toDto).toList();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID userId){
        User user=userService.findById(userId);
        UserDto userDto=userMapper.toDto(user);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
}
