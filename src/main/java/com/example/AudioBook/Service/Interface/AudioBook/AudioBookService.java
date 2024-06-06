package com.example.AudioBook.Service.Interface.AudioBook;

import com.example.AudioBook.DTO.AudioBook.ContentBook;
import com.example.AudioBook.DTO.AudioBook.DetailBook;
import com.example.AudioBook.DTO.AudioBook.TypeBook;
import com.example.AudioBook.DTO.ReviewBook.ReviewRequest;
import com.example.AudioBook.DTO.ReviewBook.ReviewResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AudioBookService {
    List<TypeBook> recommendBooks(String userId, Pageable pageable);
    List<TypeBook> bestSellerBooks(Pageable pageable);
    List<TypeBook> newReleaseBooks(Pageable pageable);
    List<TypeBook> trendingBooks(Pageable pageable);
    List<TypeBook> getBooksByNameOrAuthor(String text, Pageable pageable);
    List<TypeBook> getBooksByType(String type, Pageable pageable);
    List<TypeBook> getBooksByType(String userId, String type, Pageable pageable);
    List<TypeBook> getBooksByCategories(List<String> categories, Pageable pageable);
    DetailBook getBookById(String bookId);
    ReviewResponse reviewBook(ReviewRequest reviewRequest);
    ContentBook getContentBook(String bookId);
}
