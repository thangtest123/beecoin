package com.example.beecoin.services;

import com.example.beecoin.DTOs.ResultDTO;
import com.example.beecoin.DTOs.SystemDTO;
import com.example.beecoin.enums.StatusEnum;

public interface SystemService {
    ResultDTO create(SystemDTO systemDTO);

    ResultDTO update(SystemDTO systemDTO, String id);

    ResultDTO delete(String id);

    ResultDTO search(Integer page, Integer pageSize);

    ResultDTO getById(String id);

    ResultDTO active(String id, StatusEnum status);
}
