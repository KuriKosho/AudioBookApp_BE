package com.example.AudioBook.DTO.AudioBook;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ContentBook {
    private String id;
    private String bookName;
    private String authorName;
    private List<String> content;
}
