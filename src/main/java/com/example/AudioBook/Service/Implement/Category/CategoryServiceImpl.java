package com.example.AudioBook.Service.Implement.Category;

import com.example.AudioBook.Converter.CategoryConverter;
import com.example.AudioBook.DTO.CategoryDTO.CategoryDTO;
import com.example.AudioBook.Model.Category;
import com.example.AudioBook.Repository.Interface.CategoryRepo;
import com.example.AudioBook.Service.Interface.Category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final CategoryConverter categoryConverter;

    @Override
    public void addCategory(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public void deleteCategoryById(String categoryId) {
        categoryRepo.deleteById(categoryId);
    }

    @Override
    public void updateCategory(String categoryId, String categoryName) {
        Category category = categoryRepo.findById(categoryId).get();
        category.setName(categoryName);
        categoryRepo.save(category);
    }

    @Override
    public void getAllCategories() {
        categoryRepo.findAll();
    }

    @Override
    public List<CategoryDTO> getCategoryPageable(Pageable pageable) {
        List<Category> listCategory = categoryRepo.findAll(pageable).getContent();
        return categoryConverter.toListCategoryDTO(listCategory);
    }

    @Override
    public List<CategoryDTO> getRandomCategory() {
        List<Category> listCategory = categoryRepo.findAll();
        List<CategoryDTO> listCategoryDTO = new ArrayList<>();
        for (int i = 1; i<=5; i++) {
            int randomIndex = (int) (Math.random() * listCategory.size());
            Category category = listCategory.get(randomIndex);
            listCategory.remove(randomIndex);
            listCategoryDTO.add(categoryConverter.toCategoryDTO(category));
        }
        return listCategoryDTO;
    }
}
