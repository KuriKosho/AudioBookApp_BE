package com.example.AudioBook.DTO.ReviewBook;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    private String message;
    private boolean isSuccess;
}
