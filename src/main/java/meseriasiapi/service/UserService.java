package meseriasiapi.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import meseriasiapi.domain.Role;
import meseriasiapi.domain.User;
import meseriasiapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static meseriasiapi.exceptions.messages.Messages.*;

@Service
@AllArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User findById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException(NO_USER_WITH_THIS_ID_FOUND+id);
        }
        return user.get();

    }

    public User findByEmail(String userEmail) {
        Optional<User> user=userRepository.findByEmail(userEmail);
        if (user.isEmpty()) {
            throw new EntityNotFoundException(NO_USER_WITH_THIS_EMAIL_FOUND+userEmail);
        }
        return user.get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean checkIfRoleIsInEnum(String role) {
        try {
            Role.valueOf(role);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }


    public User createUser(User user) {
        System.out.println(user);
        if (!checkIfRoleIsInEnum(user.getRole().name())) {
            throw new EntityNotFoundException(ROLE_DOES_NOT_EXIST);
        }

        Optional<User> isUserFound = userRepository.findByEmail(user.getEmail());
        if (isUserFound.isEmpty()) {
            return userRepository.save(user);
        }
        throw new EntityExistsException(USER_ALREADY_EXISTS);

    }

    public User updateUser(User newUser) {

        if (!checkIfRoleIsInEnum(newUser.getRole().name())) {
            throw new EntityNotFoundException(ROLE_DOES_NOT_EXIST);
        }
        Optional<User> existingUserById = userRepository.findById(newUser.getId());
        if (existingUserById.isEmpty()) {
            throw new EntityNotFoundException(NO_USER_WITH_THIS_ID_FOUND);
        }

        User existingUser = existingUserById.get();

        User updatedUser = User.builder()
                .id(existingUser.getId())
                .email(newUser.getEmail())
                .password(passwordEncoder.encode(newUser.getPassword())) //need to be encrypted
                .role(newUser.getRole())
                .phone(newUser.getPhone())
                .rating(newUser.getRating())
                .creationDate(existingUser.getCreationDate())
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .description(newUser.getDescription())
                .yearsOfExperience(newUser.getYearsOfExperience())
                .build();

        return userRepository.save(updatedUser);
    }


}
