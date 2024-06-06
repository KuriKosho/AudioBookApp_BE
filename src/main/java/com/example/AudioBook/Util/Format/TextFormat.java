package com.example.AudioBook.Util.Format;

import org.springframework.stereotype.Component;

@Component
public class TextFormat {
    public String replaceDoubleSpacesAndTrim(String input) {
        // Replace double spaces with single space
        String singleSpace = input.replaceAll("\\s{2,}", " ");
        // Trim the resulting string
        String trimmed = singleSpace.trim();
        return trimmed;
    }
}
