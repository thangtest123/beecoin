package com.example.beecoin.services;

import com.example.beecoin.DTOs.ResultDTO;
import com.example.beecoin.DTOs.TransactionDTO;

public interface TransactionService {
    ResultDTO create(TransactionDTO dto);
}
