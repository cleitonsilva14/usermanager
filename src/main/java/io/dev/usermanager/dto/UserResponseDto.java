package io.dev.usermanager.dto;

import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
public class UserResponseDto {
    
    private Integer local;
    
    private String username;
    
    private String password;

    private String role;
}
