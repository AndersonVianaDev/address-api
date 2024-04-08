package com.anderson.address_api.api.controllers;

import com.anderson.address_api.core.dtos.AddressRequestDTO;
import com.anderson.address_api.core.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/addresses")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResponseEntity<Void> insert(@RequestBody AddressRequestDTO dto) {
        this.service.insert(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
