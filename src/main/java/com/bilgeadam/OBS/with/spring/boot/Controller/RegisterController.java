package com.bilgeadam.OBS.with.spring.boot.Controller;

import com.bilgeadam.OBS.with.spring.boot.Entity.User;
import com.bilgeadam.OBS.with.spring.boot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    UserRepository repository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/save")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String email = user.getEmail();
        String password = (passwordEncoder.encode(user.getPassword()));
        User userFromDb = repository.findUserByEmail(email);
        if (userFromDb == null) {
            repository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("New User Successfully Registered");
        } else {
            return ResponseEntity.internalServerError().body("This email already EXIST !");
        }
    }
}
