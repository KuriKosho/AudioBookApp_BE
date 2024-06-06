package com.example.AudioBook.Controller;

import com.example.AudioBook.DTO.CategoryDTO.CategoryDTO;
import com.example.AudioBook.DTO.Response.AudioBookResponse;
import com.example.AudioBook.Service.Interface.Category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@Transactional
public class CategoryController {
    private final CategoryService categoryService;

    // Get category by pageable
    @GetMapping("/category")
    public List<CategoryDTO> getCategoryPageable(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page-1, size, Sort.unsorted());
        return categoryService.getCategoryPageable(pageable);
    }
    @GetMapping("/category/random")
    public AudioBookResponse getRandomCategory(){
        List<CategoryDTO> listCategory = categoryService.getRandomCategory();
        if (listCategory.isEmpty()) {
            return AudioBookResponse.builder()
                    .success(false)
                    .message("No category found")
                    .data(null)
                    .build();
        }
        return AudioBookResponse.builder()
                .success(true)
                .message("Get random category successfully")
                .data(listCategory)
                .build();
    }
}
