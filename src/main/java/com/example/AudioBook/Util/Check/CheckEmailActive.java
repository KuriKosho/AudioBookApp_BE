package com.example.AudioBook.Util.Check;

import com.example.AudioBook.Util.Regex.EmailRegex;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class CheckEmailActive {
    private final EmailRegex emailRegex ;
    private final RestTemplate restTemplate;
    //    Method to check domain of email
    private boolean isDomainValid(String email) {
        String domain = email.substring(email.indexOf("@") + 1);
        try {
            restTemplate.getForEntity("https://dns.google/resolve?name=" + domain, String.class);
            return true; // DNS lookup successful
        } catch (Exception e) {
            return false; // DNS lookup failed
        }
    }

    public boolean isValidEmailAddress(String email) {
        boolean check = false;
        if (!email.isEmpty()){
            if (isDomainValid(email) && emailRegex.isValidEmail(email)) {
                check = true;
            }
        }
        return check;
    }
}
