//package com.example.AudioBook.Config.Data;
//
//
//import com.example.AudioBook.Model.Category;
//import com.example.AudioBook.Repository.Interface.CategoryRepo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class LoadDataCategory {
//    private final CategoryRepo categoryRepo;
//    @Bean
//    public CommandLineRunner loadCategory() {
//        return args -> {
//            categoryRepo.deleteAll();
//            categoryRepo.save(Category.builder().name("History").build());
//            categoryRepo.save(Category.builder().name("Science").build());
//            categoryRepo.save(Category.builder().name("Literature").build());
//            categoryRepo.save(Category.builder().name("Technology").build());
//            categoryRepo.save(Category.builder().name("Education").build());
//            categoryRepo.save(Category.builder().name("Health").build());
//            categoryRepo.save(Category.builder().name("Business").build());
//            categoryRepo.save(Category.builder().name("Biography").build());
//            categoryRepo.save(Category.builder().name("Fiction").build());
//            categoryRepo.save(Category.builder().name("Romance").build());
//            categoryRepo.save(Category.builder().name("Thriller").build());
//            categoryRepo.save(Category.builder().name("Adventure").build());
//            categoryRepo.save(Category.builder().name("Drama").build());
//            categoryRepo.save(Category.builder().name("Science Fiction").build());
//            categoryRepo.save(Category.builder().name("Mystery").build());
//            categoryRepo.save(Category.builder().name("Fantasy").build());
//            categoryRepo.save(Category.builder().name("Horror").build());
//            categoryRepo.save(Category.builder().name("Comedy").build());
//            categoryRepo.save(Category.builder().name("Action").build());
//            categoryRepo.save(Category.builder().name("Documentary").build());
//        };
//    }
//}
