package com.example.AudioBook.DTO.Authentication;

import com.example.AudioBook.DTO.AudioUser.UserInfo;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String message;
    private boolean isLogin;
    private String token;
    private UserInfo userInfo;
}
