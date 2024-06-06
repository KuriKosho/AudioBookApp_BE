package com.example.AudioBook.Util.Check;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CheckValidPassword {
    public boolean checkPassword (String password) {
        boolean kt = password != null && password.length() > 3;
        System.out.println("Password: "+password+ " , is"+ kt);
        return kt;
    }
}
