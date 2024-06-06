package com.example.AudioBook.DTO.AudioUser;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserInfo {
    private String userId;
    private String displayName;
    private String username;
    private String email;
    private String avatar;
    private String phone;
    private String dob;
}
