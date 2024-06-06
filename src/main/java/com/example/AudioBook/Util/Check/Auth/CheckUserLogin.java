package com.example.AudioBook.Util.Check.Auth;


import com.example.AudioBook.Util.Check.CheckValidPassword;
import com.example.AudioBook.Util.Check.CheckValidUsername;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckUserLogin {
    private final CheckValidUsername checkValidUsername = new CheckValidUsername() ;
    private final CheckValidPassword checkValidPassword = new CheckValidPassword();
    public boolean checkUserLogin(String username, String password) {
        return checkValidPassword.checkPassword(password) && checkValidUsername.checkUsername(username);
    }
}
