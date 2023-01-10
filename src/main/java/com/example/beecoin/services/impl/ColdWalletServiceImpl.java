package com.example.beecoin.services.impl;

import com.example.beecoin.DTOs.ColdWalletDTO;
import com.example.beecoin.DTOs.ColdWalletResultDTO;
import com.example.beecoin.DTOs.ResultDTO;
import com.example.beecoin.DTOs.WalletDTO;
import com.example.beecoin.enums.StatusEnum;
import com.example.beecoin.models.ColdWallet;
import com.example.beecoin.models.System;
import com.example.beecoin.models.Wallet;
import com.example.beecoin.repositories.ColdWalletRepository;
import com.example.beecoin.repositories.SystemRepository;
import com.example.beecoin.repositories.WalletRepository;
import com.example.beecoin.services.ColdWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ColdWalletServiceImpl implements ColdWalletService {
    @Autowired
    ColdWalletRepository coldWalletRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    SystemRepository systemRepository;

    @Override
    public ResultDTO create(ColdWalletDTO dto) {
        System checkToken = systemRepository.findByToken(dto.getToken().trim());
        if (checkToken == null)
            return new ResultDTO("400", "Token do not exist!", null);

        Wallet wallet = walletRepository.findByUsername(dto.getUsername().trim());
        if (wallet == null)
            return new ResultDTO("400", "Wallet do not exist!", null);

        if (dto.getCoins() > wallet.getUsedCoins() || dto.getCoins() > wallet.getTotalCoins())
            return new ResultDTO("400", "Cold coins is smaller than used coins. Cold coins is smaller than total coins!", null);

        ColdWallet checkColdWallet = coldWalletRepository.findColdWalletByWalletId(wallet.getId());
        if (checkColdWallet != null)
            return new ResultDTO("400", "Cold Wallet exist!", null);

        ColdWallet coldWallet = new ColdWallet();
        coldWallet.setWalletId(wallet.getId());
        coldWallet.setStatus(dto.getStatus());
        coldWallet.setCoins(dto.getCoins());
        coldWallet.setCreatedDate(LocalDateTime.now());
        coldWallet.setSystemId(checkToken.getId());
        coldWalletRepository.save(coldWallet);

        if (dto.getStatus().equals(StatusEnum.ACTIVE)) {
            Long usedCoins = wallet.getUsedCoins() - dto.getCoins();
            wallet.setUsedCoins(usedCoins);
            walletRepository.save(wallet);
        }

        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setToken(checkToken.getToken());
        walletDTO.setTotalCoins(wallet.getTotalCoins());
        walletDTO.setUsedCoins(wallet.getUsedCoins());
        walletDTO.setUsername(wallet.getUsername());

        ColdWalletResultDTO coldWalletResultDTO = new ColdWalletResultDTO();
        coldWalletResultDTO.setColdWallet(dto);
        coldWalletResultDTO.setWallet(walletDTO);
        return new ResultDTO("200", "Save successfully!", coldWalletResultDTO);
    }

    @Override
    public ResultDTO update(ColdWalletDTO dto) {
        System checkToken = systemRepository.findByToken(dto.getToken().trim());
        if (checkToken == null)
            return new ResultDTO("400", "Token do not exist!", null);

        Wallet wallet = walletRepository.findByUsername(dto.getUsername().trim());
        if (wallet == null)
            return new ResultDTO("400", "Wallet do not exist!", null);

        if (dto.getCoins() > wallet.getUsedCoins() || dto.getCoins() > wallet.getTotalCoins())
            return new ResultDTO("400", "Cold coins is smaller than used coins. Cold coins is smaller than total coins!", null);

        ColdWallet coldWallet = coldWalletRepository.findColdWalletByWalletId(wallet.getId());
        if (coldWallet == null)
            return new ResultDTO("400", "Cold wallet do not exist!", null);

        coldWallet.setWalletId(wallet.getId());
        coldWallet.setStatus(dto.getStatus());
        coldWallet.setCoins(dto.getCoins());
        coldWallet.setCreatedDate(LocalDateTime.now());
        coldWallet.setSystemId(checkToken.getId());
        coldWalletRepository.save(coldWallet);

        if (dto.getStatus().equals(StatusEnum.ACTIVE)) {
            Long usedCoins = wallet.getUsedCoins() - dto.getCoins();
            wallet.setUsedCoins(usedCoins);
            walletRepository.save(wallet);
        }

        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setToken(checkToken.getToken());
        walletDTO.setTotalCoins(wallet.getTotalCoins());
        walletDTO.setUsedCoins(wallet.getUsedCoins());
        walletDTO.setUsername(wallet.getUsername());

        ColdWalletResultDTO coldWalletResultDTO = new ColdWalletResultDTO();
        coldWalletResultDTO.setColdWallet(dto);
        coldWalletResultDTO.setWallet(walletDTO);
        return new ResultDTO("200", "Save successfully!", coldWalletResultDTO);
    }

    @Override
    public ResultDTO delete(String username, String token) {
        System checkToken = systemRepository.findByToken(token.trim());
        if (checkToken == null)
            return new ResultDTO("400", "Token do not exist!", null);

        Wallet wallet = walletRepository.findByUsername(username.trim());
        if (wallet == null)
            return new ResultDTO("400", "Wallet do not exist!", null);

        ColdWallet coldWallet = coldWalletRepository.findColdWalletByWalletId(wallet.getId());
        if (coldWallet == null)
            return new ResultDTO("400", "Cold wallet do not exist!", null);

        coldWalletRepository.delete(coldWallet);
        return new ResultDTO("200", "Delete successfully!", null);
    }

    @Override
    public ResultDTO infoByUsername(String username, String token) {
        System checkToken = systemRepository.findByToken(token.trim());
        if (checkToken == null)
            return new ResultDTO("400", "Token do not exist!", null);

        Wallet wallet = walletRepository.findByUsername(username.trim());
        if (wallet == null)
            return new ResultDTO("400", "Wallet do not exist!", null);

        ColdWallet coldWallet = coldWalletRepository.findColdWalletByWalletId(wallet.getId());
        if (coldWallet == null)
            return new ResultDTO("400", "Cold wallet do not exist!", null);

        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setToken(checkToken.getToken());
        walletDTO.setTotalCoins(wallet.getTotalCoins());
        walletDTO.setUsedCoins(wallet.getUsedCoins());
        walletDTO.setUsername(wallet.getUsername());

        ColdWalletDTO coldWalletDTO = new ColdWalletDTO();
        coldWalletDTO.setStatus(coldWallet.getStatus());
        coldWalletDTO.setCoins(coldWallet.getCoins());
        coldWalletDTO.setToken(checkToken.getToken());
        coldWalletDTO.setUsername(wallet.getUsername());

        ColdWalletResultDTO coldWalletResultDTO = new ColdWalletResultDTO();
        coldWalletResultDTO.setColdWallet(coldWalletDTO);
        coldWalletResultDTO.setWallet(walletDTO);
        return new ResultDTO("200", "Search successfully!", coldWalletResultDTO);
    }

    @Override
    public ResultDTO listByToken(String token) {
        System checkToken = systemRepository.findByToken(token.trim());
        if (checkToken == null)
            return new ResultDTO("400", "Token do not exist!", null);

        List<ColdWallet> listData = coldWalletRepository.findBySystemId(checkToken.getId());
        List<ColdWalletDTO> coldWalletDTOList = new ArrayList<>();
        if (listData.size() > 0) {
            for (ColdWallet data : listData) {
                Wallet wallet = walletRepository.findWalletById(data.getWalletId());
                ColdWalletDTO dto = new ColdWalletDTO();
                if (wallet == null)
                    dto.setUsername(null);
                else
                    dto.setUsername(wallet.getUsername());

                dto.setToken(checkToken.getToken());
                dto.setStatus(data.getStatus());
                dto.setCoins(data.getCoins());
                coldWalletDTOList.add(dto);
            }
        }
        return new ResultDTO("200", "Search successfully!", coldWalletDTOList);
    }
}
