package com.example.beecoin.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "log")
@Data
public class Log {
    @Id
    ObjectId id;

    String apiName;

    String name;

    @CreatedDate
    LocalDateTime createdDate;
}
