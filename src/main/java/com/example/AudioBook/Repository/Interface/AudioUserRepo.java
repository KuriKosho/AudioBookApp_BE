package com.example.AudioBook.Repository.Interface;

import com.example.AudioBook.Model.AudioUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AudioUserRepo extends MongoRepository<AudioUser, String>{
    Optional<AudioUser> findAudioUserByUsername(String username);
    AudioUser findAudioUserByEmail(String email);
    AudioUser findAudioUserByOtp(String otp);
    AudioUser getAudioUserById(String id);
}

