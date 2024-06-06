package com.example.AudioBook.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(collection = "Category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @MongoId(value = FieldType.STRING)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
}
