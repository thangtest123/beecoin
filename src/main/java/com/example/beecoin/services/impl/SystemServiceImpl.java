package com.example.beecoin.services.impl;

import com.example.beecoin.DTOs.ResultDTO;
import com.example.beecoin.DTOs.SystemDTO;
import com.example.beecoin.DTOs.SystemListDTO;
import com.example.beecoin.DTOs.SystemSearchDTO;
import com.example.beecoin.enums.StatusEnum;
import com.example.beecoin.models.System;
import com.example.beecoin.repositories.SystemRepository;
import com.example.beecoin.services.SystemService;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.types.ObjectId;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SystemServiceImpl implements SystemService {
    @Autowired
    SystemRepository systemRepository;

    @Autowired
    DozerBeanMapper mapper;

    @Override
    public ResultDTO create(SystemDTO systemDTO) {
        System system = new System();
        system.setName(systemDTO.getName());
        system.setStatus(StatusEnum.ACTIVE);
        system.setIp(systemDTO.getIp());
        system.setApiCheckUser(systemDTO.getApiCheckUser());
        system.setToken(genSecretKey(systemDTO.getName()));
        system.setCreatedDate(LocalDateTime.now());
        systemRepository.save(system);
        return new ResultDTO("200", "Save successfully!", systemDTO);
    }

    @Override
    public ResultDTO update(SystemDTO systemDTO, String id) {
        System system = systemRepository.findSystemById(new ObjectId(id));
        if (system == null)
            return new ResultDTO("400", "System do not exist!", null);

        system.setName(systemDTO.getName());
        system.setStatus(StatusEnum.ACTIVE);
        system.setIp(systemDTO.getIp());
        system.setToken(genSecretKey(systemDTO.getName()));
        system.setUpdatedDate(LocalDateTime.now());
        system.setApiCheckUser(systemDTO.getApiCheckUser());
        systemRepository.save(system);
        return new ResultDTO("200", "Save successfully!", systemDTO);
    }

    @Override
    public ResultDTO delete(String id) {
        System system = systemRepository.findSystemById(new ObjectId(id));
        if (system == null)
            return new ResultDTO("400", "System do not exist!", null);

        systemRepository.delete(system);
        return new ResultDTO("200", "Delete successfully!", null);
    }

    @Override
    public ResultDTO search(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "createdDate");
        List<SystemSearchDTO> systemSearchDTOS = new ArrayList<>();
        int totalPage = 0;
        long totalSize = 0;
        Page<System> systemPage = systemRepository.findAll(pageable);
        totalPage = systemPage.getTotalPages();
        totalSize = systemPage.getTotalElements();
        systemSearchDTOS = systemPage.stream().map(o -> mapper.map(o, SystemSearchDTO.class)).collect(Collectors.toList());

        SystemListDTO systemListDTO = new SystemListDTO();
        systemListDTO.setSystemSearchDTOS(systemSearchDTOS);
        systemListDTO.setTotalPage(totalPage);
        systemListDTO.setTotalSize(totalSize);

        return new ResultDTO("200", "Search successfully!", systemListDTO);
    }

    @Override
    public ResultDTO getById(String id) {
        System system = systemRepository.findSystemById(new ObjectId(id));
        if (system == null)
            return new ResultDTO("400", "System do not exist!", null);

        SystemSearchDTO systemSearchDTO = new SystemSearchDTO();
        systemSearchDTO = mapper.map(system, SystemSearchDTO.class);

        return new ResultDTO("200", "Search successfully!", systemSearchDTO);
    }

    @Override
    public ResultDTO active(String id, StatusEnum status) {
        System system = systemRepository.findSystemById(new ObjectId(id));
        if (system == null)
            return new ResultDTO("400", "System do not exist!", null);

        system.setStatus(status);
        systemRepository.save(system);
        return new ResultDTO("200", "Success!", null);
    }

    private String genSecretKey(String key) {
        String sha3Hex = new DigestUtils("SHA-512").digestAsHex(key);
        return sha3Hex;
    }
}
