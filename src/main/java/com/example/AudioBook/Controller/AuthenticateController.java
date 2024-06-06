package com.example.AudioBook.Controller;

import com.example.AudioBook.DTO.Authentication.*;
import com.example.AudioBook.Service.Interface.Authenticate.AuthenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AuthenticateController {
    private final AuthenService service;
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register (
            @RequestBody RegisterRequest request
    ){
        System.out.println(request.toString());
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate (
            @RequestBody LoginRequest request
    ){
        System.out.println(request.toString());
        LoginResponse loginResponse = service.login(request);
        if (loginResponse==null)
            return ResponseEntity.ok(LoginResponse.builder()
                    .message("Unauthorized")
                    .token(null)
                    .isLogin(false)
                    .build());
        else
            return ResponseEntity.ok(loginResponse);
    }
    @PutMapping("/verify-account")
    public ResponseEntity<VerifyOTP> verifyAccount(@RequestParam String email,
                                                   @RequestParam String otp) {
        return new ResponseEntity<>(service.verifyAccount(email, otp), HttpStatus.OK);
    }
    @PutMapping("/regenerate-otp")
    public ResponseEntity<ResendOTP> regenerateOtp(@RequestParam String email) {
        return new ResponseEntity<>(service.regenerateOtp(email), HttpStatus.OK);
    }
    @PutMapping("/forgot-password")
    public ResponseEntity<ForgotPassword> forgotPassword(@RequestParam String email) {
        return new ResponseEntity<>(service.forgotPassword(email), HttpStatus.OK);
    }
}
