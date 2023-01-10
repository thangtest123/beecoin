package com.example.beecoin.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "system_log")
@Data
public class SystemLog {
    @Id
    ObjectId id;

    String name;

    String ip;

    @CreatedDate
    LocalDateTime createdDate;
}
