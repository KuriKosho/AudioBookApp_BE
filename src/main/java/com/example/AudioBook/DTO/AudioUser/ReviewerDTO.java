package com.example.AudioBook.DTO.AudioUser;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ReviewerDTO {
    private String reviewerId;
    private String reviewerName;
    private String reviewerAvatar;
    private String reviewContent;
    private int numberStar;
    private String reviewDate;
}
