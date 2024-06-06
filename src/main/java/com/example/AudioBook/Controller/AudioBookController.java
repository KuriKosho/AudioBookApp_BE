package com.example.AudioBook.Controller;

import com.example.AudioBook.DTO.AudioBook.DetailBook;
import com.example.AudioBook.DTO.AudioBook.TypeBook;
import com.example.AudioBook.DTO.Response.AudioBookResponse;
import com.example.AudioBook.DTO.ReviewBook.ReviewRequest;
import com.example.AudioBook.DTO.ReviewBook.ReviewResponse;
import com.example.AudioBook.Service.Interface.AudioBook.AudioBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/audio-book")
@RequiredArgsConstructor
@Transactional
public class AudioBookController {
    private final AudioBookService audioBookService;

    @GetMapping("/type-book")
    public List<TypeBook> getBooksByType(
            @RequestParam String userId,
            @RequestParam String type ,
            @RequestParam int page,
            @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.unsorted());
        return audioBookService.getBooksByType(userId,type, pageable);
    }

    @GetMapping("/search-book")
    public AudioBookResponse getBooksByTypeOrAuthor(
            @RequestParam(required = false) String searchString,
            @RequestParam int page,
            @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.unsorted());
        List<TypeBook> books = audioBookService.getBooksByNameOrAuthor(searchString, pageable);
        if (books.isEmpty()) {
            return AudioBookResponse.builder()
                    .success(false)
                    .message("No book found")
                    .data(null)
                    .build();
        }
        return AudioBookResponse.builder()
                .success(true)
                .message("Get books by type or author successfully")
                .data(books)
                .build();
    }

    @GetMapping("/search-book-by-type")
    public AudioBookResponse getBooksByType(
            @RequestParam String type,
            @RequestParam int page,
            @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.unsorted());
        List<TypeBook> books = audioBookService.getBooksByType(type, pageable);
        if (books.isEmpty()) {
            return AudioBookResponse.builder()
                    .success(false)
                    .message("No book found")
                    .data(null)
                    .build();
        }
        return AudioBookResponse.builder()
                .success(true)
                .message("Get books by type successfully")
                .data(books)
                .build();
    }
    @PostMapping("/search-book/categories")
    public AudioBookResponse getBooksByCategories(
            @RequestBody List<String> categories,
            @RequestParam int page,
            @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.unsorted());
        List<TypeBook> books = audioBookService.getBooksByCategories(categories, pageable);
        if (books.isEmpty()) {
            return AudioBookResponse.builder()
                    .success(false)
                    .message("No book found")
                    .data(null)
                    .build();
        }
        return AudioBookResponse.builder()
                .success(true)
                .message("Get books by categories successfully")
                .data(books)
                .build();
    }
    @GetMapping("/detail-book")
    public DetailBook getBookById(@RequestParam String bookId) {
        return audioBookService.getBookById(bookId);
    }
    @PostMapping("/review-book")
    public ReviewResponse reviewBook(@RequestBody ReviewRequest reviewRequest) {
        return audioBookService.reviewBook(reviewRequest);
    }
    @GetMapping("/content-book")
    public Object getContentBook(@RequestParam String bookId) {
        return audioBookService.getContentBook(bookId);
    }
}
