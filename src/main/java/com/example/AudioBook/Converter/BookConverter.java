package com.example.AudioBook.Converter;

import com.example.AudioBook.DTO.AudioBook.ContentBook;
import com.example.AudioBook.DTO.AudioBook.DetailBook;
import com.example.AudioBook.DTO.AudioBook.TypeBook;
import com.example.AudioBook.Model.AudioBook;
import com.example.AudioBook.Model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookConverter {
    private final AudioUserConverter userConverter;
    public TypeBook toTypeBook(AudioBook audioBook){
        return TypeBook.builder()
                .id(audioBook.getId())
                .imgUrl(audioBook.getImageUrl())
                .bookName(audioBook.getBookName())
                .authorName(audioBook.getAuthorName())
                .numberListener(audioBook.getNumberView())
                .numberStar(audioBook.getNumberStar())
                .audioUrl(audioBook.getAudioUrl())
                .build();
    }
    public List<TypeBook> toListTypeBook(List<AudioBook> audioBooks){
        return audioBooks.stream().map(this::toTypeBook).toList();
    }
    public DetailBook toDetailBook(AudioBook audioBook){
        List<String> categories = audioBook.getTypes().stream().map(Category::getName).toList();
        return DetailBook.builder()
                .id(audioBook.getId())
                .imgUrl(audioBook.getImageUrl())
                .bookName(audioBook.getBookName())
                .authorName(audioBook.getAuthorName())
                .numberListener(audioBook.getNumberView())
//                .numberStar(audioBook.getNumberStar())
                .numberStar(audioBook.getNumberStar())
                .categories(categories)
                .summary(audioBook.getSummary())
                .audioUrl(audioBook.getAudioUrl())
                .reviews(userConverter.toListReviewerDTO(audioBook.getReviewers()))
                .numberReview(audioBook.getNumberReview())
                .build();
    }
    public ContentBook toContentBook(AudioBook audioBook){
        return ContentBook.builder()
                .id(audioBook.getId())
                .bookName(audioBook.getBookName())
                .authorName(audioBook.getAuthorName())
                .content(audioBook.getContent())
                .build();
    }
    public List<ContentBook> toListContentBook(List<AudioBook> audioBooks){
        return audioBooks.stream().map(this::toContentBook).toList();
    }
}
