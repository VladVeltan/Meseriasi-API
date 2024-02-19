package meseriasiapi.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import meseriasiapi.domain.User;
import meseriasiapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static meseriasiapi.exceptions.messages.Messages.ACCOUNT_TYPE_DOES_NOT_EXIST;
import static meseriasiapi.exceptions.messages.Messages.USER_ALREADY_EXISTS;

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

    public User createUser(User user) {
        ///remember to modify this according to modifing role to be a ENUM
        if (!Objects.equals(user.getRole(), "ADMIN") && !Objects.equals(user.getRole(), "HANDYMAN") && !Objects.equals(user.getRole(), "USER")) {
            throw new EntityNotFoundException(ACCOUNT_TYPE_DOES_NOT_EXIST);
        }

        Optional<User> isUserFound = userRepository.findByEmail(user.getEmail());
        if (isUserFound.isEmpty()) {
            //encode password
            return userRepository.save(user);
        }
        throw new EntityExistsException(USER_ALREADY_EXISTS);

    }
}
