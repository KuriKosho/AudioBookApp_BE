package com.example.AudioBook.Converter;


import com.example.AudioBook.DTO.AudioUser.ReviewerDTO;
import com.example.AudioBook.DTO.AudioUser.UserInfo;
import com.example.AudioBook.Model.AudioUser;
import com.example.AudioBook.Model.Reviewer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AudioUserConverter {
    public ReviewerDTO toReviewerDTO(Reviewer reviewer) {
        return ReviewerDTO.builder()
                .reviewerId(reviewer.getId())
                .reviewerName(reviewer.getAudioUser().getDisplayName())
                .reviewerAvatar(reviewer.getAudioUser().getProfileImg())
                .reviewDate(reviewer.getReviewTime().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .reviewContent(reviewer.getReview())
                .numberStar(reviewer.getNumberStar())
                .build();
    }
    public List<ReviewerDTO> toListReviewerDTO(List<Reviewer> reviewers){
        return reviewers.stream().map(this::toReviewerDTO).toList();
    }
    public UserInfo toUserInfo(AudioUser audioUser) {
        String str = "";
        String displayName = "";
        String email = "";
        String dob = "";
        String phone = "";
        if (audioUser.getProfileImg()==null || audioUser.getProfileImg().isEmpty()) {
            str = (audioUser.getUsername().charAt(0) + "").toUpperCase();
        } else {
            str = (audioUser.getProfileImg());
        }
        if (audioUser.getDisplayName()!=null && !audioUser.getDisplayName().isEmpty()) {
            displayName = audioUser.getDisplayName();
        }
        if (audioUser.getEmail()!=null && !audioUser.getEmail().isEmpty()) {
            email = audioUser.getEmail();
        }
        if (audioUser.getDob()!=null) {
            dob = audioUser.getDob().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        if (audioUser.getPhone()!=null && !audioUser.getPhone().isEmpty()) {
            phone = audioUser.getPhone();
        }

        return UserInfo.builder()
                .userId(audioUser.getId())
                .displayName(displayName)
                .email(email)
                .username(audioUser.getUsername())
                .phone(phone)
                .avatar(str)
                .dob(dob)
                .build();
    }
    public List<UserInfo> toListUserInfo(List<AudioUser> audioUsers){
        return audioUsers.stream().map(this::toUserInfo).toList();
    }
    public AudioUser toAudioUser(UserInfo userInfo){
        return AudioUser.builder()
                .id(userInfo.getUserId())
                .displayName(userInfo.getDisplayName())
                .email(userInfo.getEmail())
                .username(userInfo.getUsername())
                .phone(userInfo.getPhone())
                .profileImg(userInfo.getAvatar())
                .dob(java.time.LocalDate.parse(userInfo.getDob(), java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();
    }
    public AudioUser toAudioUser(UserInfo userInfo, AudioUser audioUser){
        audioUser.setDisplayName(userInfo.getDisplayName());
        audioUser.setEmail(userInfo.getEmail());
        audioUser.setUsername(userInfo.getUsername());
        audioUser.setPhone(userInfo.getPhone());
        audioUser.setProfileImg(userInfo.getAvatar());
        audioUser.setDob(java.time.LocalDate.parse(userInfo.getDob(), java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return audioUser;
    }

    public List<AudioUser> toListAudioUser(List<UserInfo> userInfos){
        return userInfos.stream().map(this::toAudioUser).toList();
    }

}
