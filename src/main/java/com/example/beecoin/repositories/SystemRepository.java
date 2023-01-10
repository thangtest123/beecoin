package com.example.beecoin.repositories;

import com.example.beecoin.models.System;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends MongoRepository<System, ObjectId> {
    System findSystemById(ObjectId id);

    System findByToken(String token);
}
