package com.example.AudioBook.DTO.AudioUser;


import com.example.AudioBook.DTO.CategoryDTO.CategoryDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AudioUserCategory {
    private String audioUserId;
    private List<CategoryDTO> categories;
}
