package com.example.beecoin.models;

import com.example.beecoin.enums.StatusEnum;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "transaction")
@Data
public class Transaction {
    @Id
    ObjectId id;

    String sender;

    String recipient;

    Long coins;

    String transactionCode;

    String content;

    ObjectId systemId;

    @CreatedDate
    LocalDateTime createdDate;
}
