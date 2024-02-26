package com.artur.orange_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserCreationDTO {
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email field must match email pattern")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*\\d)[A-Za-z\\d]{7,}$", message = "Password must match pattern")
    private String password;

    @NotBlank(message = "Matching password is mandatory")
    @Pattern(regexp = "^(?=.*\\d)[A-Za-z\\d]{7,}$", message = "Password must match pattern")
    private String matchingPassword;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private byte[] profileImg;
}
