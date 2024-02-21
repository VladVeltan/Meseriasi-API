package meseriasiapi.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import meseriasiapi.domain.Role;
import meseriasiapi.domain.User;
import meseriasiapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

import static meseriasiapi.exceptions.messages.Messages.*;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("No user found");
        } else {
            return user.get();
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean checkIfRoleIsInEnum(String role){
        List<String> enumValues= Arrays
                .stream(Role.values())
                .map(Enum::name).toList();

        return enumValues.contains(role);
    }
    public User createUser(User user) {

        if (!checkIfRoleIsInEnum(user.getRole().name()) || user.getRole()!=null) {
            throw new EntityNotFoundException(ROLE_DOES_NOT_EXIST);
        }

        Optional<User> isUserFound = userRepository.findByEmail(user.getEmail());
        if (isUserFound.isEmpty()) {
            return userRepository.save(user);
        }
        throw new EntityExistsException(USER_ALREADY_EXISTS);

    }

    public User updateUser(User newUser){

        if (!checkIfRoleIsInEnum(newUser.getRole().name()) || newUser.getRole()!=null) {
            throw new EntityNotFoundException(ROLE_DOES_NOT_EXIST);
        }

        Optional<User> existingUserOptional = userRepository.findByEmail(newUser.getEmail());
        if (existingUserOptional.isEmpty()) {
           throw new EntityNotFoundException(NO_SUCH_USER_FOUND);
        }

        User existingUser = existingUserOptional.get();

        User updatedUser = User.builder()
                .id(existingUser.getId())
                .email(newUser.getEmail())
                .password(newUser.getPassword())
                .role(newUser.getRole())
                .phone(newUser.getPhone())
                .media(newUser.getMedia())
                .rating(newUser.getRating())
                .build();

        return userRepository.save(updatedUser);
    }
}
