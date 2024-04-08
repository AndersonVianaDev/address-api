package com.anderson.address_api.api.controllers;

import com.anderson.address_api.core.dtos.AddressRequestDTO;
import com.anderson.address_api.core.dtos.AddressResponseDTO;
import com.anderson.address_api.core.dtos.AddressUpdateDTO;
import com.anderson.address_api.core.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/find/{id}")
    public ResponseEntity<AddressResponseDTO> findById(@PathVariable UUID id) {
        AddressResponseDTO dto = this.service.findById(id).toAddressResponseDTO();

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddressResponseDTO> update(@PathVariable UUID id, @RequestBody AddressUpdateDTO dto) {
        AddressResponseDTO dtoResponse = this.service.update(id, dto).toAddressResponseDTO();

        return ResponseEntity.status(HttpStatus.OK).body(dtoResponse);
    }
}
