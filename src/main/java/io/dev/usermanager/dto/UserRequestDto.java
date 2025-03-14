package io.dev.usermanager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public class UserRequestDto {
    
    @NotNull
    @Min(1)
    private Integer local;
    
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;

    @NotBlank
    private String role;

}
