package com.example.AudioBook.Service.Implement.AudioUser;

import com.example.AudioBook.Converter.AudioUserConverter;
import com.example.AudioBook.Converter.BookConverter;
import com.example.AudioBook.Converter.CategoryConverter;
import com.example.AudioBook.DTO.AudioBook.TypeBook;
import com.example.AudioBook.DTO.AudioUser.UserInfo;
import com.example.AudioBook.DTO.CategoryDTO.CategoryDTO;
import com.example.AudioBook.Model.AudioBook;
import com.example.AudioBook.Model.AudioUser;
import com.example.AudioBook.Model.Category;
import com.example.AudioBook.Repository.Interface.AudioBookRepo;
import com.example.AudioBook.Repository.Interface.AudioUserRepo;
import com.example.AudioBook.Service.Interface.AudioUser.AudioUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class AudioUserServiceImpl implements AudioUserService {
    private final AudioUserRepo audioUserRepo;
    private final CategoryConverter categoryConverter;
    private final AudioUserConverter audioUserConverter;
    private final AudioBookRepo audioBookRepo;
    private final BookConverter bookConverter;

    //    Update category of user
    @Override
    public void updateAudioUserCategory(String userId, String categoryId) {
        Optional<AudioUser> userOptional = audioUserRepo.findById(userId);
        if (userOptional.isPresent()) {
            AudioUser user = userOptional.get();
            Category category = new Category();
            category.setId(categoryId);
            user.getCategoryList().add(category);
            audioUserRepo.save(user);
        }
    }
    @Override
    public boolean updateAudioUserCategories(String userId, List<CategoryDTO> categories) {
        Optional<AudioUser> userOptional = audioUserRepo.findById(userId);
        if (userOptional.isPresent()) {
            AudioUser user = userOptional.get();
            user.setCategoryList(categoryConverter.toListCategory(categories));
            audioUserRepo.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAudioUserInfo(UserInfo userInfo) {
        Optional<AudioUser> userOptional = audioUserRepo.findById(userInfo.getUserId());
        if (userOptional.isPresent()) {
            AudioUser user = userOptional.get();
            user = audioUserConverter.toAudioUser(userInfo, user);
            audioUserRepo.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<TypeBook> getAudioUserLibraryBook(String userId) {
        Optional<AudioUser> userOptional = audioUserRepo.findById(userId);
        if (userOptional.isPresent()) {
            AudioUser user = userOptional.get();
            return user.getAudioBookList().stream().map(typeBook -> TypeBook.builder()
                    .id(typeBook.getId())
                    .audioUrl(typeBook.getAudioUrl())
                    .authorName(typeBook.getAuthorName())
                    .bookName(typeBook.getBookName())
                    .imgUrl(typeBook.getImageUrl())
                    .numberListener(typeBook.getNumberListener())
                    .numberStar(typeBook.getNumberStar())
                    .build()).toList();
        }
        return List.of();
    }

    @Override
    public TypeBook addBookToLibrary(String userId, String bookId) {
        AudioBook book = audioBookRepo.findById(bookId).orElse(null);
        if (book == null) {
            return null;
        }
        Optional<AudioUser> userOptional = audioUserRepo.findById(userId);
        if (userOptional.isPresent()) {
            AudioUser user = userOptional.get();
            user.getAudioBookList().add(book);
            audioUserRepo.save(user);
            return bookConverter.toTypeBook(book);
        }
        return null;
    }

    @Override
    public boolean removeBookFromLibrary(String userId, String bookId) {
        AudioBook book = audioBookRepo.findById(bookId).orElse(null);
        if (book == null) {
            return false;
        }
        Optional<AudioUser> userOptional = audioUserRepo.findById(userId);
        if (userOptional.isPresent()) {
            AudioUser user = userOptional.get();
            if (!user.getAudioBookList().contains(book)) {
                return false;
            }
            user.getAudioBookList().remove(book);
            audioUserRepo.save(user);
            return true;
        }
        return false;
    }
}

