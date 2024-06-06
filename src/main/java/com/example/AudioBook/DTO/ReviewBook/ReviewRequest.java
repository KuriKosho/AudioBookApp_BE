package com.example.AudioBook.DTO.ReviewBook;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private String bookId;
    private String userId;
    private int numberStar;
    private String review;
}
