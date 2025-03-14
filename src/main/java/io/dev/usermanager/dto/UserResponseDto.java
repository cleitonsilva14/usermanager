package io.dev.usermanager.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UserResponseDto {
    
    private Integer local;
    
    private String username;
    
    private String role;
}
