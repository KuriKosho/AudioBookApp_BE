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

@Document(collection = "Reviewer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reviewer {
    @Id
    @MongoId(value = FieldType.STRING)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String bookId;
    private int numberStar;
    private LocalDateTime reviewTime;
    private String review;
    @DBRef
    private AudioUser audioUser;
}
