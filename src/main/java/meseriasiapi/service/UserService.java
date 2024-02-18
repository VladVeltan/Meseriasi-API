package meseriasiapi.service;

import lombok.AllArgsConstructor;
import meseriasiapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
