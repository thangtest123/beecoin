package com.example.beecoin.controllers;

import com.example.beecoin.DTOs.ColdWalletDTO;
import com.example.beecoin.DTOs.ResultDTO;
import com.example.beecoin.services.ColdWalletService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Cold Wallet Controller API")
@CrossOrigin
public class ColdWalletController {
    @Autowired
    ColdWalletService coldWalletService;

    @RequestMapping(value = "/cold-wallet/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDTO> create(@RequestBody ColdWalletDTO dto) {
        return new ResponseEntity<ResultDTO>(coldWalletService.create(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/cold-wallet/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDTO> update(@RequestBody ColdWalletDTO dto) {
        return new ResponseEntity<ResultDTO>(coldWalletService.update(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/cold-wallet/delete/{username}/{token}", method = RequestMethod.DELETE)
    public ResponseEntity<ResultDTO> delete(@PathVariable String username,
                                            @PathVariable String token) {
        return new ResponseEntity<ResultDTO>(coldWalletService.delete(username, token), HttpStatus.OK);
    }

    @RequestMapping(value = "/cold-wallet/info-by-username", method = RequestMethod.GET)
    public ResponseEntity<ResultDTO> infoByUsername(@RequestParam(name = "username") String username,
                                                    @RequestParam(name = "token") String token) {
        return new ResponseEntity<ResultDTO>(coldWalletService.infoByUsername(username, token), HttpStatus.OK);
    }

    @RequestMapping(value = "/cold-wallet/list-by-token", method = RequestMethod.GET)
    public ResponseEntity<ResultDTO> listByToken(@RequestParam(name = "token") String token) {
        return new ResponseEntity<ResultDTO>(coldWalletService.listByToken(token), HttpStatus.OK);
    }
}
