package com.example.AudioBook.DTO.Authentication;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPassword {
    private String message;
    private boolean isSent;
}
