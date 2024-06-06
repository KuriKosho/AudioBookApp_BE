package com.example.AudioBook.Util.Check.Auth;

import com.example.AudioBook.DTO.Authentication.RegisterRequest;

import com.example.AudioBook.DTO.Authentication.RegisterResponse;
import com.example.AudioBook.Model.AudioUser;
import com.example.AudioBook.Repository.Interface.AudioUserRepo;
import com.example.AudioBook.Util.Check.CheckEmailActive;
import com.example.AudioBook.Util.Check.CheckValidName;
import com.example.AudioBook.Util.Check.CheckValidPassword;
import com.example.AudioBook.Util.Check.CheckValidUsername;
import com.example.AudioBook.Util.Regex.EmailRegex;
import com.example.AudioBook.Util.Regex.NameRegex;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class CheckUserRegister {
    private final AudioUserRepo userRepo;
    private final CheckEmailActive active = new CheckEmailActive(new EmailRegex(), new RestTemplate()) ;
    private final CheckValidUsername checkUsername = new CheckValidUsername();
    private final CheckValidName checkName = new CheckValidName(new NameRegex());
    private final CheckValidPassword checkPassword = new CheckValidPassword();

    public RegisterResponse checkEmailRegister(String email){
        RegisterResponse response = null;
        AudioUser a_user = userRepo.findAudioUserByEmail(email);
        if ( a_user==null ) {
            if (!active.isValidEmailAddress(email)) {
                return RegisterResponse.builder()
                        .message("Email is invalid")
                        .email(email)
                        .isRegister(false)
                        .build();
            };
        } else {
            if (!a_user.isActive()) {
                userRepo.delete(a_user);
                if (!active.isValidEmailAddress(email)) {
                    return RegisterResponse.builder()
                            .message("Email is invalid")
                            .email(email)
                            .isRegister(false)
                            .build();
                };
            } else {
                return RegisterResponse.builder()
                        .message("Email is already registered")
                        .email(email)
                        .isRegister(false)
                        .build();
            }
        }

        return null;
    }
    public RegisterResponse checkUsernameRegister(String username){
        RegisterResponse response = null;
        try {
            AudioUser a_user = userRepo.findAudioUserByUsername(username).isPresent() ? userRepo.findAudioUserByUsername(username).get() : null;
            if ( a_user==null ) {
                if (!checkUsername.checkUsername(username)) {
                    return RegisterResponse.builder()
                            .message("Username is invalid")
                            .isRegister(false)
                            .build();
                };
            } else {
                return RegisterResponse.builder()
                        .message("Username is already registered")
                        .isRegister(false)
                        .build();
            }
        } catch (Exception e) {
            return RegisterResponse.builder()
                    .message("Username is invalid")
                    .isRegister(false)
                    .build();
        }
        return null;
    }
    public boolean checkUserRegister(RegisterRequest user){
        return checkEmailRegister(user.getEmail())==null && checkUsernameRegister(user.getUsername())==null && checkPassword.checkPassword(user.getPassword());
    }

}
