package com.example.AudioBook.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "AudioBook")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AudioBook {
    @Id
    @MongoId(value = FieldType.STRING)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String imageUrl;
    private String bookName;
    private String authorName;
    private int numberStar;
    @DBRef
    private List<Category> types;
    private int numberReview;
    private int numberView;
    private int numberListener;
    private List<String> content;
    private List<String> summary;
    private String audioUrl;
    private String audioLength;
    private LocalDate releaseDate;

    @DBRef
    private List<Reviewer> reviewers;

}
