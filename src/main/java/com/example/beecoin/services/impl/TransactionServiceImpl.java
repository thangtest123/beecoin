package com.example.beecoin.services.impl;

import com.example.beecoin.DTOs.ResultDTO;
import com.example.beecoin.DTOs.TransactionDTO;
import com.example.beecoin.models.System;
import com.example.beecoin.models.Transaction;
import com.example.beecoin.models.Wallet;
import com.example.beecoin.repositories.SystemRepository;
import com.example.beecoin.repositories.TransactionRepository;
import com.example.beecoin.repositories.WalletRepository;
import com.example.beecoin.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    SystemRepository systemRepository;

    @Override
    public ResultDTO create(TransactionDTO dto) {
        System checkToken = systemRepository.findByToken(dto.getToken().trim());
        if (checkToken == null)
            return new ResultDTO("400", "Token do not exist!", null);

        Wallet sender = walletRepository.findByUsername(dto.getSender().trim());
        if (sender == null)
            return new ResultDTO("400", "Mover do not exist!", null);

        Wallet recipient = walletRepository.findByUsername(dto.getRecipient());
        if (recipient == null)
            return new ResultDTO("400", "Recipient do not exist!", null);

        if (dto.getCoins() > sender.getUsedCoins())
            return new ResultDTO("400", "Sender does not have enough money", null);

        sender.setUsedCoins(sender.getUsedCoins() - dto.getCoins());
        walletRepository.save(sender);

        recipient.setUsedCoins(recipient.getUsedCoins() + dto.getCoins());
        walletRepository.save(recipient);

        String transactionCodeString = sender.getUsername() + recipient.getUsername() + LocalDateTime.now();
        byte[] transactionCodeByte = transactionCodeString.getBytes(StandardCharsets.UTF_8);
        String transactionCode = new String(transactionCodeByte, Charset.forName("UTF-8"));

        Transaction transaction = new Transaction();
        transaction.setSender(dto.getSender());
        transaction.setRecipient(dto.getRecipient());
        transaction.setCoins(dto.getCoins());
        transaction.setTransactionCode(transactionCode);
        transaction.setContent(dto.getContent());
        transaction.setSystemId(checkToken.getId());
        transaction.setCreatedDate(LocalDateTime.now());
        transactionRepository.save(transaction);
        return new ResultDTO("200", "Transaction is created!", dto);
    }
}
