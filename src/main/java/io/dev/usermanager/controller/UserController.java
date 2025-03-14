package io.dev.usermanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.dev.usermanager.model.UserModel;
import io.dev.usermanager.service.UserService;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;



    @GetMapping
    public ResponseEntity<List<UserModel>> findAll() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.findAll());
    }



    @GetMapping("/{username}")
    public ResponseEntity<UserModel> findByUsername(@PathVariable String username) {
        Optional<UserModel> userOptional = userService.findByUsername(username);
    
        if (!userOptional.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userOptional.get());
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserModel>> findByRole(@PathVariable String role) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findByRole(role));
    }

    @GetMapping("/local/{local}")
    public ResponseEntity<List<UserModel>> findByLocal(@PathVariable Integer local) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findByLocal(local));
    }
    
    
    

    @PostMapping
    public ResponseEntity<UserModel> save(@RequestBody UserModel user) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.save(user));
    }
    

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestParam String username, @RequestBody UserModel userModel){
        
        Optional<UserModel> userOptional = userService.findByUsername(username);

        if (!userOptional.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        UserModel user = userOptional.get();

        user.setPassword(userModel.getPassword());

        userService.save(user);


        return ResponseEntity.status(HttpStatus.OK)
            .body(String.format("Senha do usuário %s alterada!", user.getUsername()));
    }


}
