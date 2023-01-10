package com.example.beecoin.models;

import com.example.beecoin.enums.StatusEnum;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "system")
@Data
public class System {
    @Id
    ObjectId id;

    String name;

    StatusEnum status;

    String token;

    String ip;

    String apiCheckUser;

    @CreatedDate
    LocalDateTime createdDate;

    @LastModifiedDate
    LocalDateTime updatedDate;
}
