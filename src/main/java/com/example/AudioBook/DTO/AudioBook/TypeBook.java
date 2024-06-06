package com.example.AudioBook.DTO.AudioBook;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TypeBook {
    private String id;
    private String imgUrl;
    private String bookName;
    private String authorName;
    private int numberStar;
    private int numberListener;
    private String audioUrl;
}
