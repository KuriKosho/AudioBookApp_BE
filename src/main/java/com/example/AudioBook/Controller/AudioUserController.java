package com.example.AudioBook.Controller;

import com.example.AudioBook.DTO.AudioBook.TypeBook;
import com.example.AudioBook.DTO.AudioUser.AudioUserCategory;
import com.example.AudioBook.DTO.AudioUser.UserInfo;
import com.example.AudioBook.DTO.CategoryDTO.CategoryDTO;
import com.example.AudioBook.DTO.Response.AudioBookResponse;
import com.example.AudioBook.Model.Category;
import com.example.AudioBook.Service.Interface.AudioUser.AudioUserService;
import com.example.AudioBook.Service.Interface.Authenticate.AuthenService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/audio-user")
@RequiredArgsConstructor
@Transactional
public class AudioUserController {
    private final AudioUserService userService;

    @PostMapping("/update-category")
    public void updateAudioUserCategory(String userId, String categoryId) {
        userService.updateAudioUserCategory(userId, categoryId);
    }
    @PostMapping("/update-categories")
    public boolean updateAudioUserCategories(@RequestBody AudioUserCategory request) {
        return  userService.updateAudioUserCategories(request.getAudioUserId(), request.getCategories());
    }
    @PostMapping("/update-info")
    public boolean updateAudioUserInfo(@RequestBody UserInfo userInfo) {
        System.out.println(userInfo.getUserId());
        return userService.updateAudioUserInfo(userInfo);
    }
    @GetMapping("/get-library")
    public AudioBookResponse getAudioUserLibraryBook(@RequestParam String userId)
    {
        List<TypeBook> books = userService.getAudioUserLibraryBook(userId);
        if (books.isEmpty()) {
            return AudioBookResponse.builder()
                    .success(false)
                    .message("No book found")
                    .data(null)
                    .build();
        }
        return AudioBookResponse.builder()
                .success(true)
                .message("Get library book successfully")
                .data(books)
                .build();
    }
    @PostMapping("/add-book")
    public AudioBookResponse addBookToLibrary(@RequestParam String userId, @RequestParam String bookId) {
//        return userService.addBookToLibrary(userId, bookId);
        try {
            return AudioBookResponse.builder()
                    .success(true)
                    .message("Add book to library successfully")
                    .data(userService.addBookToLibrary(userId, bookId))
                    .build();
        } catch (Exception e) {
            return AudioBookResponse.builder()
                    .success(false)
                    .message("Add book to library failed")
                    .data(null)
                    .build();
        }
    }

    @PostMapping("/remove-book")
    public AudioBookResponse removeBookFromLibrary(@RequestParam String userId, @RequestParam String bookId) {
//        return userService.removeBookFromLibrary(userId, bookId);
        try {
            return AudioBookResponse.builder()
                    .success(userService.removeBookFromLibrary(userId, bookId))
                    .message("Remove book from library successfully")
                    .data(null)
                    .build();
        } catch (Exception e) {
            return AudioBookResponse.builder()
                    .success(false)
                    .message("Remove book from library failed")
                    .data(null)
                    .build();
        }
    }

}
