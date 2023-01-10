package com.example.beecoin.models;

import com.example.beecoin.enums.StatusEnum;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "cold_wallet")
@Data
public class ColdWallet {
    @Id
    ObjectId id;

    ObjectId walletId;

    StatusEnum status;

    Long coins;

    ObjectId systemId;

    @CreatedDate
    LocalDateTime createdDate;

    @LastModifiedDate
    LocalDateTime updatedDate;
}
