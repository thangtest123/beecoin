package com.example.beecoin.services.impl;

import com.example.beecoin.DTOs.ResultDTO;
import com.example.beecoin.DTOs.WalletDTO;
import com.example.beecoin.models.System;
import com.example.beecoin.models.Wallet;
import com.example.beecoin.repositories.SystemRepository;
import com.example.beecoin.repositories.WalletRepository;
import com.example.beecoin.services.WalletService;
import com.google.gson.Gson;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WalletServiceImpl implements WalletService {
    @Autowired
    WalletRepository walletRepository;

    @Autowired
    SystemRepository systemRepository;

    @Autowired
    DozerBeanMapper mapper;

    @Override
    public ResultDTO create(WalletDTO dto) {
        if (dto.getUsedCoins() > dto.getTotalCoins())
            return new ResultDTO("400", "Used coins are less than total coins", null);
        System checkToken = systemRepository.findByToken(dto.getToken().trim());
        if (checkToken == null)
            return new ResultDTO("400", "Token do not exist!", null);

        String apiUser = checkToken.getApiCheckUser();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(apiUser)
                                .queryParam("username", "{username}")
                                .encode()
                                .toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("username", dto.getUsername().trim());

        HttpEntity<String> response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                entity,
                String.class,
                params
        );

        Gson gson = new Gson();
        ResultDTO resultDTO = gson.fromJson(response.getBody(), ResultDTO.class);
        if (resultDTO.getStatus().equals("200")) {
            Wallet checkWallet = walletRepository.findByUsername(dto.getUsername().trim());
            if (checkWallet != null)
                return new ResultDTO("400", "Wallet exist!", null);

            Wallet wallet = new Wallet();
            wallet.setSystemId(checkToken.getId());
            wallet.setUsername(dto.getUsername());
            wallet.setTotalCoins(dto.getTotalCoins());
            wallet.setUsedCoins(dto.getUsedCoins());
            wallet.setCreatedDate(LocalDateTime.now());
            walletRepository.save(wallet);
            return new ResultDTO("200", "Save successfully", dto);
        }

        return new ResultDTO("400","Save failure", null);
    }

    @Override
    public ResultDTO update(WalletDTO dto) {
        System checkToken = systemRepository.findByToken(dto.getToken().trim());
        if (checkToken == null)
            return new ResultDTO("400", "Token do not exist!", null);

        Wallet wallet = walletRepository.findByUsername(dto.getUsername().trim());
        if (wallet == null)
            return new ResultDTO("400", "Wallet do not exist!", null);

        wallet.setSystemId(checkToken.getId());
        wallet.setUsername(dto.getUsername());
        wallet.setTotalCoins(dto.getTotalCoins());
        wallet.setUsedCoins(dto.getUsedCoins());
        wallet.setUpdatedDate(LocalDateTime.now());
        walletRepository.save(wallet);
        return new ResultDTO("200", "Save successfully!", dto);
    }

    @Override
    public ResultDTO delete(String username, String token) {
        System checkToken = systemRepository.findByToken(token.trim());
        if (checkToken == null)
            return new ResultDTO("400", "Token do not exist!", null);

        Wallet wallet = walletRepository.findByUsername(username.trim());
        if (wallet == null)
            return new ResultDTO("400", "Wallet do not exist!", null);

        walletRepository.delete(wallet);
        return new ResultDTO("200", "Delete successfully!", null);
    }

    @Override
    public ResultDTO infoWalletByUsername(String username, String token) {
        System checkToken = systemRepository.findByToken(token.trim());
        if (checkToken == null)
            return new ResultDTO("400", "Token do not exist!", null);

        Wallet wallet = walletRepository.findByUsername(username.trim());
        if (wallet == null)
            return new ResultDTO("400", "Wallet do not exist!", null);

        WalletDTO dto = new WalletDTO();
        dto.setUsername(wallet.getUsername());
        dto.setTotalCoins(wallet.getTotalCoins());
        dto.setUsedCoins(wallet.getUsedCoins());
        dto.setToken(checkToken.getToken());
        return new ResultDTO("200", "Search successfully!", dto);
    }

    @Override
    public ResultDTO listWalletByToken(String token) {
        System checkToken = systemRepository.findByToken(token.trim());
        if (checkToken == null)
            return new ResultDTO("400", "Token do not exist!", null);

        List<Wallet> listData = walletRepository.findBySystemId(checkToken.getId());
        List<WalletDTO> walletDTOList = new ArrayList<>();
        if (listData.size() > 0) {
            for (Wallet data : listData) {
                WalletDTO walletDTO = new WalletDTO();
                walletDTO.setToken(checkToken.getToken());
                walletDTO.setUsername(data.getUsername());
                walletDTO.setTotalCoins(data.getTotalCoins());
                walletDTO.setUsedCoins(data.getUsedCoins());
                walletDTOList.add(walletDTO);
            }
        }
        return new ResultDTO("200", "Search successfully!", walletDTOList);
    }

}
