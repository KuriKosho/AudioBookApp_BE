package com.example.AudioBook.Config.Data;

import com.example.AudioBook.Model.AudioBook;
import com.example.AudioBook.Model.AudioUser;
import com.example.AudioBook.Model.Category;
import com.example.AudioBook.Repository.Interface.AudioBookRepo;
import com.example.AudioBook.Repository.Interface.AudioUserRepo;
import com.example.AudioBook.Repository.Interface.CategoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@AllArgsConstructor
public class LoadDataBook {
    private final AudioBookRepo audioBookRepository;
    private final CategoryRepo categoryRepo;
    private final AudioUserRepo audioUserRepo;
    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            categoryRepo.deleteAll();
            categoryRepo.save(Category.builder().name("History").build());
            categoryRepo.save(Category.builder().name("Science").build());
            categoryRepo.save(Category.builder().name("Literature").build());
            categoryRepo.save(Category.builder().name("Technology").build());
            categoryRepo.save(Category.builder().name("Education").build());
            categoryRepo.save(Category.builder().name("Health").build());
            categoryRepo.save(Category.builder().name("Business").build());
            categoryRepo.save(Category.builder().name("Biography").build());
            categoryRepo.save(Category.builder().name("Fiction").build());
            categoryRepo.save(Category.builder().name("Romance").build());
            categoryRepo.save(Category.builder().name("Thriller").build());
            categoryRepo.save(Category.builder().name("Adventure").build());
            categoryRepo.save(Category.builder().name("Drama").build());
            categoryRepo.save(Category.builder().name("Science Fiction").build());
            categoryRepo.save(Category.builder().name("Mystery").build());
            categoryRepo.save(Category.builder().name("Fantasy").build());
            categoryRepo.save(Category.builder().name("Horror").build());
            categoryRepo.save(Category.builder().name("Comedy").build());
            categoryRepo.save(Category.builder().name("Action").build());
            categoryRepo.save(Category.builder().name("Documentary").build());

            audioBookRepository.deleteAll();
            String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed malesuada convallis mi, sed vestibulum metus mollis id. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Sed at condimentum orci. Nulla facilisi.";

            List<String> content = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                content.add("Chapter " + (i + 1) + ": " + loremIpsum);
            }

            List<String> summary = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                summary.add("Summary " + (i + 1) + ": " + loremIpsum);
            }
            List<String> listImg = new ArrayList<>();
            for (int i=1 ; i<=20 ; i++) {
                listImg.add("https://media.newyorker.com/photos/661560a0859cd787c3b6a58a/master/w_1000,c_limit/the%20limits.jpg");
                listImg.add("https://media.newyorker.com/photos/661560a0de5c95a58f56e63b/master/w_1000,c_limit/cocktails%20with%20george%20and%20martha.jpg");
                listImg.add("https://media.newyorker.com/photos/661560a01efd5aa132c7a3be/master/w_1000,c_limit/cahokia%20jazz.jpg");
                listImg.add("https://media.newyorker.com/photos/661563b2c62092a4440bdeda/master/w_1000,c_limit/the%20wide%20wide%20sea.jpg");
                listImg.add("https://assets.vogue.com/photos/65fb5a1877db5e347b4933dc/3:4/w_640,c_limit/strange%20eventful.jpg");
                listImg.add("https://assets.vogue.com/photos/65a185c3367731376015c812/3:4/w_640,c_limit/slide_9.jpg");
            }
            List<AudioBook> audioBooks = new ArrayList<>();
            for (int i = 0; i < 40; i++) {
                AudioBook audioBook = AudioBook.builder()
                        .imageUrl(listImg.get(i))  // Set imageUrl to null
                        .bookName("Book " + i)
                        .authorName("Author " + i)
                        .numberStar(i % 5 + 1)  // Varying number of stars
                        .types(Collections.singletonList(categoryRepo.findAll().get(i % 20)))
                        .numberReview(i * 10)
                        .content(content)  // Set content to the long content list
                        .summary(summary)  // Set summary to the long summary list
                        .audioUrl("https://archive.org/details/aesop_fables_volume_one_librivox/fables_01_00_aesop.mp3")
                        .audioLength("10:00")  // Assuming fixed audio length
                        .releaseDate(LocalDate.now())
                        .numberView(i*10)
                        .numberStar(i%5+1)
                        .reviewers(new ArrayList<>())
                        .audioUrl("https://archive.org/details/aesop_fables_volume_one_librivox/fables_01_00_aesop.mp3")
                        .build();
                audioBookRepository.save(audioBook);
                if (i<20) {
                    audioBooks.add(audioBook);
                }
            }
            AudioUser user = audioUserRepo.getAudioUserById("66430fc2af9efe719d977d57");
            user.setAudioBookList(audioBooks);
            audioUserRepo.save(user);
        };
    }
}
//
//@Configuration
//public class LoadData {
//    @Bean
//    public CommandLineRunner initDatabase(AudioBookRepo audioBookRepository) {
//        return args -> {
//            List<String> types1 = Arrays.asList("Fiction", "Thriller");
//            List<String> types2 = Arrays.asList("Science Fiction", "Adventure");
//            List<String> types3 = Arrays.asList("Romance", "Drama");
//
//            List<String> content1 = Arrays.asList("Chapter 1", "Chapter 2", "Chapter 3");
//            List<String> content2 = Arrays.asList("Chapter 1", "Chapter 2", "Chapter 3", "Chapter 4");
//            List<String> content3 = Arrays.asList("Chapter 1", "Chapter 2", "Chapter 3", "Chapter 4", "Chapter 5");
//
//            List<String> summary1 = Arrays.asList("Summary 1", "Summary 2");
//            List<String> summary2 = Arrays.asList("Summary 1", "Summary 2", "Summary 3");
//            List<String> summary3 = Arrays.asList("Summary 1", "Summary 2", "Summary 3", "Summary 4");
//
//            List<AudioBook> audioBooks = new ArrayList<>();
//            for (int i = 0; i < 20; i++) {
//                AudioBook audioBook = AudioBook.builder()
//                        .imageUrl("image-url-" + i)
//                        .bookName("Book " + i)
//                        .authorName("Author " + i)
//                        .numberStar(i % 5 + 1)  // Varying number of stars
//                        .types(i % 3 == 0 ? types1 : (i % 3 == 1 ? types2 : types3))
//                        .numberReview(i * 10)
//                        .content(i % 3 == 0 ? content1 : (i % 3 == 1 ? content2 : content3))
//                        .summary(i % 3 == 0 ? summary1 : (i % 3 == 1 ? summary2 : summary3))
//                        .audioUrl("audio-url-" + i)
//                        .audioLength("10:00")  // Assuming fixed audio length
//                        .description("Description " + i)
//                        .build();
//                audioBooks.add(audioBook);
//            }
//            audioBookRepository.saveAll(audioBooks);
//        };
//    }
//}
