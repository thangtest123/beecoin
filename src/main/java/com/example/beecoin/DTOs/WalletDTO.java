package com.example.beecoin.DTOs;

import lombok.Data;

@Data
public class WalletDTO {
    String username;

    Long totalCoins;

    Long usedCoins;

    String token;
}
