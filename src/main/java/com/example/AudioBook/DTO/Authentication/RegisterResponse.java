package com.example.AudioBook.DTO.Authentication;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private String message;
    private boolean isRegister;
    private String email;
}
