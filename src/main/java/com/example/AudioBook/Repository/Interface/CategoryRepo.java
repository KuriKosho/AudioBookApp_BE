package com.example.AudioBook.Repository.Interface;

import com.example.AudioBook.Model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepo extends MongoRepository<Category, String>{
    List<Category> findByName(String name);
    Category findCategoryByName(String name);
    @Query("{$or:[{name:{$regex:?0,$options:'i'}}]}")
    List<Category> findCategoryByNameContainsIgnoreCase(String name);

    @Query("{$or:[{id:{$regex:?0,$options:'i'}}]}")
    Category findCategoryById(String id);
}
