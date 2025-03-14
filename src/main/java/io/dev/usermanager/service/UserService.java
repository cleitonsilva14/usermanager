

package io.dev.usermanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.dev.usermanager.model.UserModel;
import io.dev.usermanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public List<UserModel> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public UserModel save(UserModel user) {
        return userRepository.save(user);
    }


    @Transactional
    public Optional<UserModel> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public List<UserModel> findByRole(String role){
        return userRepository.findByRole(role);
    }

    @Transactional
    public List<UserModel> findByLocal(Integer local){
        return userRepository.findByLocal(local);
    }

    


}
