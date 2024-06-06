package com.example.AudioBook.Service.Implement.AudioBook;


import com.example.AudioBook.Converter.BookConverter;
import com.example.AudioBook.DTO.AudioBook.ContentBook;
import com.example.AudioBook.DTO.AudioBook.DetailBook;
import com.example.AudioBook.DTO.AudioBook.TypeBook;
import com.example.AudioBook.DTO.ReviewBook.ReviewRequest;
import com.example.AudioBook.DTO.ReviewBook.ReviewResponse;
import com.example.AudioBook.Model.AudioBook;
import com.example.AudioBook.Model.AudioUser;
import com.example.AudioBook.Model.Category;
import com.example.AudioBook.Model.Reviewer;
import com.example.AudioBook.Repository.Interface.AudioBookRepo;
import com.example.AudioBook.Repository.Interface.AudioUserRepo;
import com.example.AudioBook.Repository.Interface.CategoryRepo;
import com.example.AudioBook.Repository.Interface.ReviewerRepo;
import com.example.AudioBook.Service.Interface.AudioBook.AudioBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class  AudioBookServiceImpl implements AudioBookService {
    private final AudioBookRepo audioBookRepo;
    private final AudioUserRepo audioUserRepo;
    private final ReviewerRepo reviewerRepo;
    private final CategoryRepo categoryRepo;
    private final BookConverter bookConverter;

    @Override
    public List<TypeBook> recommendBooks(String userId, Pageable pageable) {
        AudioUser a_user = audioUserRepo.findById(userId).orElse(null);
        List<TypeBook> recommendBooks = new ArrayList<>();

        if (a_user != null) {
            System.out.println("User found: " + a_user.getUsername());
            List<Category> categories = a_user.getCategoryList();
            if (categories != null && !categories.isEmpty()) {
                List<TypeBook> foundBooks = bookConverter.toListTypeBook(audioBookRepo.findAudioBooksByTypesInOrderByNumberReviewDesc(Collections.singletonList(categories), pageable));
                System.out.println("Found books: " + foundBooks.size());
                recommendBooks = foundBooks.isEmpty() ? bookConverter.toListTypeBook(audioBookRepo.findAllByOrderByNumberReviewDesc(pageable)) : foundBooks;
            } else {
                recommendBooks = bookConverter.toListTypeBook(audioBookRepo.findAllByOrderByNumberReviewDesc(pageable));
            }
        } else {
            System.out.println("User not found. Recommend all books.");
            recommendBooks = bookConverter.toListTypeBook(audioBookRepo.findAllByOrderByNumberReviewDesc(pageable));
        }

        System.out.println("Recommend books: " + recommendBooks.size());
        return recommendBooks;
    }

    @Override
    public List<TypeBook> bestSellerBooks(Pageable pageable) {
        return bookConverter.toListTypeBook(audioBookRepo.findAllByOrderByNumberStarDesc(pageable));
    }

    @Override
    public List<TypeBook> newReleaseBooks(Pageable pageable) {
        return bookConverter.toListTypeBook(audioBookRepo.findAllByOrderByReleaseDateDesc(pageable));
    }

    @Override
    public List<TypeBook> trendingBooks(Pageable pageable) {
        return bookConverter.toListTypeBook(audioBookRepo.findAllByOrderByNumberViewDesc(pageable));
    }

    @Override
    public List<TypeBook> getBooksByNameOrAuthor(String text, Pageable pageable) {
        System.out.println("Search text: " + text);
        return bookConverter.toListTypeBook(audioBookRepo.findAllByAuthorNameContainsIgnoreCaseOrBookNameContainsIgnoreCase(text, pageable));
    }

    @Override
    public List<TypeBook> getBooksByType(String type, Pageable pageable) {
        Category category = categoryRepo.findCategoryByName(type);
        if (category != null) {
            return bookConverter.toListTypeBook(audioBookRepo.getAudioBooksByTypesContainsIgnoreCase(List.of(category), pageable));
        }
        return List.of();
    }

    @Override
    public List<TypeBook> getBooksByCategories(List<String> categories, Pageable pageable) {
        List<Category> categoryList = new ArrayList<>();
        for (String id : categories) {
//            List<Category> foundCategory = categoryRepo.findCategoryByNameContainsIgnoreCase(category);
//            Category foundCategory = categoryRepo.findCategoryById(id);
            Category foundCategory = categoryRepo.findById(id).orElse(null);
            System.out.println("ID="+id);
            if (foundCategory!= null) {
//              categoryList.addAll(foundCategory);
                categoryList.add(foundCategory);
            } else {
                System.out.println("Category not found: " + id);
            }
        }
        if (!categoryList.isEmpty()) {
            return bookConverter.toListTypeBook(audioBookRepo.getAudioBooksByTypesContainsIgnoreCase(categoryList, pageable));
        }
        return List.of();
    }

    @Override
    public List<TypeBook> getBooksByType(String userId, String type, Pageable pageable) {
        return switch (type) {
            case "recommend" -> recommendBooks(userId, pageable);
            case "bestseller" -> bestSellerBooks(pageable);
            case "newrelease" -> newReleaseBooks(pageable);
            case "trending" -> trendingBooks(pageable);
            default ->
                    List.of();
        };
    }


    @Override
    public DetailBook getBookById(String bookId) {
        AudioBook audioBook = audioBookRepo.findById(bookId).isPresent() ? audioBookRepo.findById(bookId).get() : null;
        if (audioBook == null) {
            return null;
        }
        return bookConverter.toDetailBook(audioBookRepo.findById(bookId).get()) ;
    }

    @Override
    public ReviewResponse reviewBook(ReviewRequest reviewRequest) {
        AudioBook audioBook = audioBookRepo.getAudioBookById(reviewRequest.getBookId());
        AudioUser audioUser = audioUserRepo.getAudioUserById(reviewRequest.getUserId());
        if (audioBook == null) {
            System.out.println("Book not found.");
            return ReviewResponse.builder()
                    .isSuccess(false)
                    .message("Book not found.")
                    .build();
        }
        if (audioUser == null) {
            return ReviewResponse.builder()
                    .isSuccess(false)
                    .message("User not found.")
                    .build();
        }
        Reviewer reviewer = Reviewer.builder()
                .audioUser(audioUser)
                .numberStar(reviewRequest.getNumberStar())
                .review(reviewRequest.getReview())
                .reviewTime(java.time.LocalDateTime.now())
                .build();
        audioBook.getReviewers().add(reviewer);
        audioBook.setNumberReview(audioBook.getNumberReview() + 1);
        audioBook.setNumberStar((audioBook.getNumberStar() + reviewRequest.getNumberStar()) / audioBook.getNumberReview());
        reviewerRepo.save(reviewer);
        audioBookRepo.save(audioBook);

        return ReviewResponse.builder()
                .isSuccess(true)
                .message("Review success.")
                .build();

    }

    @Override
    public ContentBook getContentBook(String bookId) {
        return bookConverter.toContentBook(audioBookRepo.findById(bookId).get());
    }
}
