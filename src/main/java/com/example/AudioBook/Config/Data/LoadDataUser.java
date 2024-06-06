//package com.example.AudioBook.Config.Data;
//
//
//import com.example.AudioBook.Model.AudioUser;
//import com.example.AudioBook.Model.Category;
//import com.example.AudioBook.Repository.Interface.AudioUserRepo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.validation.constraints.Max;
//import java.util.List;
//
//@Configuration
//@RequiredArgsConstructor
//public class LoadDataUser {
//    @Bean
//    public CommandLineRunner loadUser(AudioUserRepo userRepo) {
//        return args -> {
//            userRepo.deleteAll();
//            List<Category> list = List.of(
//                    Category.builder().name("History").build(),
//                    Category.builder().name("Science").build(),
//                    Category.builder().name("Literature").build()
//            );
//            AudioUser user = AudioUser.builder()
//                    .username("vantai")
//                    .email("vuongvanthong115@gmail.com")
//                    .password("$2a$10$aIKVoclRtM1hB3y/PU5Es.Mwfswm..CtdRkpyBa0J75FvSo9q.gdK")
//                    .active(true)
//                    .dob(null)
//                    .phone("0123456789")
//                    .otp("00000")
//                    .categoryList(list)
//                    .displayName("Vuong Van Tai")
//                    .profileImg("https://media.newyorker.com/photos/661560a0859cd787c3b6a58a/master/w_1000,c_limit/the%20limits.jpg")
//                    .otpGeneratedTime(null)
//                    .build();
//            userRepo.save(user);
//        };
//    }
//}
