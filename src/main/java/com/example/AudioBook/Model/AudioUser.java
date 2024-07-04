package com.example.AudioBook.Model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(collection = "AudioUser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AudioUser implements UserDetails{
    @Id
    @MongoId(value = FieldType.STRING)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String username;
    private String password;
    private String email;
    private String otp;
    private boolean active;
    private LocalDateTime otpGeneratedTime;
    private String displayName;
    private String profileImg;
    private LocalDate dob;
    private String phone;
    private boolean isPremium;
//    @DBRef
//    private List<Reviewer> reviewerList;

    @DBRef
    private List<Category> categoryList;
    @DBRef
    private List<AudioBook> audioBookList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
