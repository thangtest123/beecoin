package com.example.beecoin.controllers;

import com.example.beecoin.DTOs.ResultDTO;
import com.example.beecoin.DTOs.WalletDTO;
import com.example.beecoin.services.WalletService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Wallet Controller API")
@CrossOrigin
public class WalletController {
    @Autowired
    WalletService walletService;

    @RequestMapping(value = "/wallet/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDTO> create(@RequestBody WalletDTO dto) {
        return new ResponseEntity<ResultDTO>(walletService.create(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/wallet/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDTO> update(@RequestBody WalletDTO dto) {
        return new ResponseEntity<ResultDTO>(walletService.update(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/wallet/delete/{username}/{token}", method = RequestMethod.DELETE)
    public ResponseEntity<ResultDTO> delete(@PathVariable String username, @PathVariable String token) {
        return new ResponseEntity<ResultDTO>(walletService.delete(username, token), HttpStatus.OK);
    }

    @RequestMapping(value = "/wallet/info-wallet-username", method = RequestMethod.GET)
    public ResponseEntity<ResultDTO> infoWalletByUsername(@RequestParam(name = "username") String username,
                                                          @RequestParam(name = "token") String token) {
        return new ResponseEntity<ResultDTO>(walletService.infoWalletByUsername(username, token), HttpStatus.OK);
    }

    @RequestMapping(value = "/wallet/list-wallet-token", method = RequestMethod.GET)
    public ResponseEntity<ResultDTO> listWalletByToken(@RequestParam(name = "token") String token) {
        return new ResponseEntity<ResultDTO>(walletService.listWalletByToken(token), HttpStatus.OK);
    }
}
