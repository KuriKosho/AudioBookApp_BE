package com.example.AudioBook.DTO.Response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AudioBookResponse {
    private boolean success;
    private String message;
    private Object data;
}
