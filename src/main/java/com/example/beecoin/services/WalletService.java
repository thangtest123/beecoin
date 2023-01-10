package com.example.beecoin.services;

import com.example.beecoin.DTOs.ResultDTO;
import com.example.beecoin.DTOs.WalletDTO;

public interface WalletService {
    ResultDTO create(WalletDTO dto);

    ResultDTO update(WalletDTO dto);

    ResultDTO delete(String username, String token);

    ResultDTO infoWalletByUsername(String username, String token);

    ResultDTO listWalletByToken(String token);
}
