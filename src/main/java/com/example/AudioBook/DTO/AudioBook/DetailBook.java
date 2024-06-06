package com.example.AudioBook.DTO.AudioBook;

import com.example.AudioBook.DTO.AudioUser.ReviewerDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DetailBook {
    private String id;
    private String imgUrl;
    private String bookName;
    private String authorName;
    private int numberListener;
    private int numberStar;
    private List<String> categories;
    private List<String> summary;
    private String audioUrl;
    private List<ReviewerDTO> reviews;
    private int numberReview;

}
