package com.example.AudioBook.Converter;

import com.example.AudioBook.DTO.Authentication.LoginRequest;
import com.example.AudioBook.Util.Format.TextFormat;
import org.springframework.stereotype.Component;

@Component
public class LoginConverter {
    private final TextFormat textUtil = new TextFormat();
    public LoginRequest formatLogin(LoginRequest request) {
        return LoginRequest.builder()
                .username(textUtil.replaceDoubleSpacesAndTrim(request.getUsername()))
                .password(request.getPassword())
                .build();
    }
}
