package com.example.beecoin.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "wallet")
@Data
public class Wallet {
    @Id
    ObjectId id;

    String username;

    Long totalCoins;

    Long usedCoins;

    ObjectId systemId;

    @CreatedDate
    LocalDateTime createdDate;

    @LastModifiedDate
    LocalDateTime updatedDate;
}
