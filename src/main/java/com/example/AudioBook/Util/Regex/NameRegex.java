package com.example.AudioBook.Util.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class NameRegex {
    private  final String regex = ".*[0-9.,/;\\-+*\\/_()\\[\\]{}&^%$#@!`~'\"\\|].*";
    private  final Pattern pattern = Pattern.compile(regex);

    public boolean checkName(String name ) {
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

}
