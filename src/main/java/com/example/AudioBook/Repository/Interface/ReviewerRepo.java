package com.example.AudioBook.Repository.Interface;

import com.example.AudioBook.Model.Reviewer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewerRepo extends MongoRepository<Reviewer, String>{
}
