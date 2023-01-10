package com.example.beecoin.controllers;

import com.example.beecoin.DTOs.ResultDTO;
import com.example.beecoin.DTOs.SystemDTO;
import com.example.beecoin.enums.StatusEnum;
import com.example.beecoin.services.SystemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "System Controller API")
@CrossOrigin
public class SystemController {
    @Autowired
    SystemService systemService;

    @RequestMapping(value = "/system/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDTO> create(@RequestBody SystemDTO dto) {
        return new ResponseEntity<ResultDTO>(systemService.create(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/system/update/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDTO> update(@RequestBody SystemDTO dto,
                                            @PathVariable String id) {
        return new ResponseEntity<ResultDTO>(systemService.update(dto, id), HttpStatus.OK);
    }

    @RequestMapping(value = "/system/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResultDTO> delete(@PathVariable String id) {
        return new ResponseEntity<ResultDTO>(systemService.delete(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/system/get-by-id", method = RequestMethod.GET)
    public ResponseEntity<ResultDTO> getById(@RequestParam(name = "id") String id) {
        return new ResponseEntity<ResultDTO>(systemService.getById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/system/search", method = RequestMethod.GET)
    public ResponseEntity<ResultDTO> search(@RequestParam(name = "page") Integer page,
                                            @RequestParam(name = "pageSize") Integer pageSize) {
        return new ResponseEntity<ResultDTO>(systemService.search(page, pageSize), HttpStatus.OK);
    }

    @RequestMapping(value = "/system/active", method = RequestMethod.GET)
    public ResponseEntity<ResultDTO> active(@RequestParam(name = "id") String id,
                                            @RequestParam(name = "status") StatusEnum status) {
        return new ResponseEntity<ResultDTO>(systemService.active(id, status), HttpStatus.OK);
    }
}
