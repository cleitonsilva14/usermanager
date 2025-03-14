package io.dev.usermanager.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.dev.usermanager.dto.UserRequestDto;
import io.dev.usermanager.dto.UserResponseDto;
import io.dev.usermanager.model.UserModel;
import io.dev.usermanager.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> userResponseList = userService.findAll().stream()
            .map(user -> {
                UserResponseDto userResponseDto = new UserResponseDto();
                BeanUtils.copyProperties(user, userResponseDto);
                return userResponseDto;
            })
            .collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(userResponseList);
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
    public ResponseEntity<?> save(@Valid @RequestBody UserRequestDto userRequestDto) {

        //log.info("{}", userRequestDto.toString());

        var user = new UserModel();
        var response = new UserResponseDto();

        BeanUtils.copyProperties(userRequestDto, user);

        userService.save(user);

        BeanUtils.copyProperties(user, response);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);

    }
    
    @PatchMapping("/password")
    public ResponseEntity<?> updateUser(@RequestParam String username, @RequestBody String newPassword){
        
        Optional<UserModel> userOptional = userService.findByUsername(username);

        if (!userOptional.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        UserModel user = userOptional.get();

        user.setPassword(newPassword);

        userService.save(user);


        return ResponseEntity.status(HttpStatus.OK)
            .body(String.format("Senha do usuário %s alterada!", user.getUsername()));
    }


}
