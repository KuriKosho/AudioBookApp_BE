package com.example.AudioBook.DTO.Authentication;

import com.example.AudioBook.DTO.AudioUser.UserInfo;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class VerifyOTP {
    private String message;
    private String token;
    private boolean isVerify;
    private UserInfo userInfo;

}
