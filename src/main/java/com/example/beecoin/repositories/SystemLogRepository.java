package com.example.beecoin.repositories;

import com.example.beecoin.models.SystemLog;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemLogRepository extends MongoRepository<SystemLog, ObjectId> {
}
