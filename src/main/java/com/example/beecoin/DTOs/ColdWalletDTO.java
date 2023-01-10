package com.example.beecoin.DTOs;

import com.example.beecoin.enums.StatusEnum;
import lombok.Data;

@Data
public class ColdWalletDTO {
    StatusEnum status;

    Long coins;

    String token;

    String username;
}
