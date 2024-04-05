package com.anderson.address_api.core.services.impl;

import com.anderson.address_api.core.dtos.AddressRequestDTO;
import com.anderson.address_api.core.repository.AddressRepository;
import com.anderson.address_api.core.services.AddressService;

public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    public AddressServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }


    @Override
    public void insert(AddressRequestDTO dto) {

    }
}
