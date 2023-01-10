package com.example.beecoin.services;

import com.example.beecoin.DTOs.ColdWalletDTO;
import com.example.beecoin.DTOs.ResultDTO;

public interface ColdWalletService {
    ResultDTO create(ColdWalletDTO dto);

    ResultDTO update(ColdWalletDTO dto);

    ResultDTO delete(String username, String token);

    ResultDTO infoByUsername(String username, String token);

    ResultDTO listByToken(String token);
}
