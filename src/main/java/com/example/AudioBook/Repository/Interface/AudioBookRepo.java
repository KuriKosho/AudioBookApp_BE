package com.example.AudioBook.Repository.Interface;


import com.example.AudioBook.Model.AudioBook;
import com.example.AudioBook.Model.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AudioBookRepo extends MongoRepository<AudioBook, String>{
    List<AudioBook> findAudioBooksByTypesInOrderByNumberReviewDesc(Collection<List<Category>> types, Pageable pageable);
    List<AudioBook> findAllByOrderByNumberReviewDesc(Pageable pageable);
    List<AudioBook> findAllByOrderByNumberStarDesc(Pageable pageable);
    List<AudioBook> findAllByOrderByReleaseDateDesc(Pageable pageable);
    List<AudioBook> findAllByOrderByNumberViewDesc(Pageable pageable);
    List<AudioBook> findAllByBookNameContainingIgnoreCaseOrAuthorNameContainingIgnoreCase(String text, Pageable pageable);
    @Query("{$or:[{authorName:{$regex:?0,$options:'i'}},{bookName:{$regex:?0,$options:'i'}}]}")
    List<AudioBook> findAllByAuthorNameContainsIgnoreCaseOrBookNameContainsIgnoreCase(String text, Pageable pageable);

    List<AudioBook> getAudioBooksByTypesContainsIgnoreCase(List<Category> types, Pageable pageable);
    AudioBook getAudioBookById(String bookId);
}
