package com.example.AudioBook.Converter;

import com.example.AudioBook.DTO.CategoryDTO.CategoryDTO;
import com.example.AudioBook.Model.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryConverter {
    public CategoryDTO toCategoryDTO(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category toCategory(CategoryDTO categoryDTO){
        return Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .build();
    }
    public List<CategoryDTO> toListCategoryDTO(List<Category> categories){
        return categories.stream().map(this::toCategoryDTO).toList();
    }
    public List<Category> toListCategory(List<CategoryDTO> categoryDTOS){
        return categoryDTOS.stream().map(this::toCategory).toList();
    }
}
