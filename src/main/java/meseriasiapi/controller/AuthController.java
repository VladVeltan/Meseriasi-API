package meseriasiapi.controller;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import meseriasiapi.domain.AuthenticationResponse;
import meseriasiapi.domain.User;
import meseriasiapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        try{
            return new ResponseEntity<>(authService.register(request), HttpStatus.OK);
        }catch(EntityExistsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {
        return new ResponseEntity<>(authService.authenticate(request), HttpStatus.OK);
    }

}
