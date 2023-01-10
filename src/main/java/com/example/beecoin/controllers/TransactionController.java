package com.example.beecoin.controllers;

import com.example.beecoin.DTOs.ResultDTO;
import com.example.beecoin.DTOs.TransactionDTO;
import com.example.beecoin.services.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Transaction Controller API")
@CrossOrigin
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "/transaction/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDTO> create(@RequestBody TransactionDTO dto) {
        return new ResponseEntity<ResultDTO>(transactionService.create(dto), HttpStatus.OK);
    }
}
