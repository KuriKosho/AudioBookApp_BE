package com.example.AudioBook.Service.Interface.Category;

import com.example.AudioBook.DTO.CategoryDTO.CategoryDTO;
import com.example.AudioBook.Model.Category;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);
    void deleteCategoryById(String categoryId);
    void updateCategory(String categoryId, String categoryName);
    void getAllCategories();
    List<CategoryDTO> getCategoryPageable(Pageable pageable);
    List<CategoryDTO> getRandomCategory();
}
