package com.example.AudioBook.Util.Check;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class CheckValidUsername {
    public boolean checkUsername(String username) {
        boolean kt = false;
        if (!username.isEmpty()) {
            kt = username.length() > 3 && username.length() < 30;
        }
        return kt;
    }
}
