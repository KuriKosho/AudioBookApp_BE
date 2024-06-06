package com.example.AudioBook.Service.Interface.AudioUser;

import com.example.AudioBook.DTO.AudioBook.TypeBook;
import com.example.AudioBook.DTO.AudioUser.UserInfo;
import com.example.AudioBook.DTO.CategoryDTO.CategoryDTO;
import com.example.AudioBook.Model.Category;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AudioUserService {
    void updateAudioUserCategory(String userId, String categoryId);
    boolean updateAudioUserCategories(String userId, List<CategoryDTO> categoryIds);
    boolean updateAudioUserInfo(UserInfo userInfo);
    List<TypeBook> getAudioUserLibraryBook(String userId);
    TypeBook addBookToLibrary(String userId, String bookId);
    boolean removeBookFromLibrary(String userId, String bookId);
}
