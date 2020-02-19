package com.example.mongodbdemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.mongodbdemo.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer>{
  public  User findByFirstName(String firtName);
}
