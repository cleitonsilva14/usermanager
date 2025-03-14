package io.dev.usermanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "local", nullable = false)
    private Integer local;

    @Column(name = "username", nullable = false, length = 40)
    private String username;
    
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false, length = 40)
    private String role;

}
