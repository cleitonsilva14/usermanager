package io.dev.usermanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.dev.usermanager.model.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    //@Query(value = "SELECT * FROM tb_user WHERE username = :username", nativeQuery =true)
    Optional<UserModel> findByUsername(String username);

    List<UserModel> findByRole(String role);

    List<UserModel> findByLocal(Integer local);



}
