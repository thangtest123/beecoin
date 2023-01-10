package com.example.beecoin.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemListDTO {
    List<SystemSearchDTO> systemSearchDTOS;

    int totalPage;

    long totalSize;
}
