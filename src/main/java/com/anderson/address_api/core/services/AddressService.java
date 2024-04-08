package com.anderson.address_api.core.services;

import com.anderson.address_api.core.dtos.AddressRequestDTO;
import com.anderson.address_api.core.model.Address;

import java.util.UUID;

public interface AddressService {

    void insert(AddressRequestDTO dto);

    Address findById(UUID id);

    void delete(UUID id);
}
