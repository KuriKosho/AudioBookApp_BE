package com.example.AudioBook.DTO.Authentication;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ResendOTP {
    private String message;
    private boolean isSend;
}
