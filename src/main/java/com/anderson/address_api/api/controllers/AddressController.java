package com.anderson.address_api.api.controllers;

import com.anderson.address_api.core.dtos.AddressRequestDTO;
import com.anderson.address_api.core.dtos.AddressResponseDTO;
import com.anderson.address_api.core.dtos.AddressUpdateDTO;
import com.anderson.address_api.core.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Create address", description = "Creates a new address.", method = "POST")
    public ResponseEntity<AddressResponseDTO> insert(@RequestBody AddressRequestDTO dto) {
        AddressResponseDTO addressResponseDTO = this.service.insert(dto).toAddressResponseDTO();

        return ResponseEntity.status(HttpStatus.CREATED).body(addressResponseDTO);
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Find address by id", description = "Finds an address by its id.", method = "GET")
    public ResponseEntity<AddressResponseDTO> findById(@PathVariable UUID id) {
        AddressResponseDTO dto = this.service.findById(id).toAddressResponseDTO();

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete address by id", description = "Deletes an address by its id.", method = "DELETE")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update address by id", description = "Updates an existing address by its id.", method = "PUT")
    public ResponseEntity<AddressResponseDTO> update(@PathVariable UUID id, @RequestBody AddressUpdateDTO dto) {
        AddressResponseDTO dtoResponse = this.service.update(id, dto).toAddressResponseDTO();

        return ResponseEntity.status(HttpStatus.OK).body(dtoResponse);
    }
}
