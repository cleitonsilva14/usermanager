package io.dev.usermanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UserRequestUpdatePasswordDto {

    @NotBlank
    private String password;

}
