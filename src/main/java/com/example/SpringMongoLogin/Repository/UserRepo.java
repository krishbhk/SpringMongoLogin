package com.example.SpringMongoLogin.Repository;

import com.example.SpringMongoLogin.Model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserModel, String> {
    UserModel findByUsername(String username);
}
