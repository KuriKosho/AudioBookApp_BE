package com.example.AudioBook.Service.Interface.Authenticate;


import com.example.AudioBook.DTO.Authentication.*;


public interface AuthenService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
    VerifyOTP verifyAccount(String email, String otp);
    ResendOTP regenerateOtp(String email);
    ForgotPassword forgotPassword(String email);
}
