package com.example.AudioBook.Converter;

import com.example.AudioBook.DTO.Authentication.RegisterRequest;
import com.example.AudioBook.Util.Format.TextFormat;
import org.springframework.stereotype.Component;

@Component
public class RegisterConverter {
    private final TextFormat textUtil = new TextFormat();
    public RegisterRequest formatRegister(RegisterRequest request) {
        return RegisterRequest.builder()
                .email(textUtil.replaceDoubleSpacesAndTrim(request.getEmail()))
                .password(textUtil.replaceDoubleSpacesAndTrim(request.getPassword()))
                .username(textUtil.replaceDoubleSpacesAndTrim(request.getUsername()))
                .build();
    }
}
