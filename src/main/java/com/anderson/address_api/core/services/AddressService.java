package com.anderson.address_api.core.services;

import com.anderson.address_api.core.dtos.AddressRequestDTO;

public interface AddressService {

    void insert(AddressRequestDTO dto);
}
