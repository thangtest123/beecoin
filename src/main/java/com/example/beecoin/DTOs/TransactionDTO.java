package com.example.beecoin.DTOs;

import lombok.Data;

@Data
public class TransactionDTO {
    String sender;

    String recipient;

    Long coins;

    String content;

    String token;
}
