package com.example.AudioBook.Util.Check;

import com.example.AudioBook.Util.Regex.NameRegex;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CheckValidName {
    private final NameRegex nameRegex;
    public  boolean checkName(String name) {
        boolean kt = name != null && name.length() > 3 && nameRegex.checkName(name);
        System.out.println("Name with: "+name+ ", is: "+ kt);
        return !kt;
    }
}
