package com.example.AudioBook.Service.Implement.Authenticate;

import com.example.AudioBook.Converter.AudioUserConverter;
import com.example.AudioBook.Converter.LoginConverter;
import com.example.AudioBook.Converter.RegisterConverter;
import com.example.AudioBook.DTO.Authentication.*;
import com.example.AudioBook.Model.AudioUser;
import com.example.AudioBook.Repository.Interface.AudioUserRepo;
import com.example.AudioBook.Service.Interface.Authenticate.AuthenService;
import com.example.AudioBook.Service.Jwt.JwtService;
import com.example.AudioBook.Util.Authentication.EmailUtil;
import com.example.AudioBook.Util.Authentication.OtpUtil;
import com.example.AudioBook.Util.Authentication.PasswordUtil;
import com.example.AudioBook.Util.Check.Auth.CheckUserLogin;
import com.example.AudioBook.Util.Check.Auth.CheckUserRegister;
import com.example.AudioBook.Util.Check.CheckValidPassword;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenServiceImpl implements AuthenService {

    private final AudioUserRepo userRepo;
    private final PasswordEncoder passwordEncoder ;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final OtpUtil otpUtil;
    private final EmailUtil emailUtil;
    private final PasswordUtil passwordUtil;
    private final CheckUserRegister checkUserRegister;
    private final CheckUserLogin checkUserLogin;
    private final LoginConverter loginConverter;
    private final RegisterConverter registerConverter;
    private final AudioUserConverter audioUserConverter;
    private final CheckValidPassword checkPassword = new CheckValidPassword();


    @Override
    public RegisterResponse register(RegisterRequest requestRegister) {
        RegisterRequest request = registerConverter.formatRegister(requestRegister);
        if (!checkUserRegister.checkUserRegister(request)) {
            RegisterResponse response = checkUserRegister.checkEmailRegister(request.getEmail());
            if (response!=null) {
                return response;
            }
            RegisterResponse response1 = checkUserRegister.checkUsernameRegister(request.getUsername());
            if (response1!=null) {
                return response1;
            }
            boolean checked = checkPassword.checkPassword(request.getPassword());
            if (!checked) {
                return RegisterResponse.builder()
                        .message("Password is invalid")
                        .email(request.getEmail())
                        .isRegister(false)
                        .build();
            }
        }
        String otp = otpUtil.generateOtp();
        System.out.println("OTP issssssssssss: "+otp);
        try {
            emailUtil.sendOtpEmail(request.getEmail(), otp);
        } catch (MessagingException e) {
            return RegisterResponse.builder()
                    .message("Error in server, please wait a few minutes !")
                    .email(null)
                    .isRegister(false)
                    .build();
        }
        var user1 = AudioUser.builder()
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .otp(otp)
                .otpGeneratedTime(LocalDateTime.now())
                .dob(null)
                .phone(null)
                .active(false)
                .username(request.getUsername())
                .profileImg(null)
                .displayName(null)
                .build();
        userRepo.save(user1);
        return RegisterResponse.builder()
                .message("Register Successfully ! Now Confirm OTP to login")
                .isRegister(true)
                .email(request.getEmail())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest requestLogin) {
        LoginRequest request = loginConverter.formatLogin(requestLogin);
        if (!checkUserLogin.checkUserLogin(request.getUsername(), request.getPassword())) {
            return LoginResponse.builder()
                    .token(null)
                    .message("Login failed, check format before login !")
                    .isLogin(false)
                    .build();
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return LoginResponse.builder()
                    .token(null)
                    .message("Login failed, Authenticate account failed !")
                    .isLogin(false)
                    .build();
        }
        AudioUser audioUser = null;
        try {
            audioUser = userRepo.findAudioUserByUsername(request.getUsername()).orElseThrow(
                    () -> new UsernameNotFoundException("User not found !")
            );
        } catch (Exception e) {
            return LoginResponse.builder()
                    .isLogin(false)
                    .message("User not found, please try again !")
                    .token(null)
                    .build();
        }
        System.out.println("Active: "+audioUser.isActive());
        if (!audioUser.isActive()) {
            return LoginResponse.builder()
                    .token(null)
                    .message("Login failed, your account is not verified !")
                    .isLogin(false)
                    .build();
        }
        var jwtToken = jwtService.generateToken(audioUser);
        System.out.println(audioUser.getId());
        return LoginResponse.builder()
                .token(jwtToken)
                .message("Login successfully")
                .isLogin(true)
                .userInfo(audioUserConverter.toUserInfo(audioUser))
                .build();
    }

    @Override
    public VerifyOTP verifyAccount(String email, String otp) {
        System.out.println("Email:"+email+" | OTP:"+otp);
        AudioUser user = userRepo.findAudioUserByEmail(email);
        if (user==null){
            return VerifyOTP.builder()
                    .isVerify(false)
                    .message("User not found to verify!")
                    .token(null)
                    .build();
        }
        if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (3 * 60)) {
            user.setActive(true);
            userRepo.save(user);
            String userId = userRepo.findAudioUserByEmail(email).getId();
            var jwtToken = jwtService.generateToken(user);
            return VerifyOTP.builder()
                    .message("Verify account successfully !")
                    .isVerify(true)
                    .token(jwtToken)
                    .userInfo(audioUserConverter.toUserInfo(user))
                    .userId(userId)
                    .build();
        }
        return VerifyOTP.builder()
                .message("Verify account failed !")
                .isVerify(false)
                .token(null)
                .build();
    }

    @Override
    public ResendOTP regenerateOtp(String email) {
        AudioUser user = userRepo.findAudioUserByEmail(email);
        if (user==null){
            return ResendOTP.builder()
                    .isSend(false)
                    .message("User not found !")
                    .build();
        }
        String otp = otpUtil.generateOtp();
        System.out.println("OTP issssssssssss: "+otp);
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepo.save(user);
        return ResendOTP.builder()
                .isSend(true)
                .message("OTP was sent !")
                .build();
    }

    @Override
    public ForgotPassword forgotPassword(String email) {
        AudioUser user = userRepo.findAudioUserByEmail(email);
        if (user==null){
            return ForgotPassword.builder()
                    .isSent(false)
                    .message("User with email "+email+" not found !")
                    .build();
        }
        String password = passwordUtil.generatePassword();
        System.out.println("Password issssssssssss: "+password);
        try {
            emailUtil.sendPasswordEmail(email, password);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send password please try again");
        }
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);
        return ForgotPassword.builder()
                .isSent(true)
                .message("Password was sent !")
                .build();
    }
}
