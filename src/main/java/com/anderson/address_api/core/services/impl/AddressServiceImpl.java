package com.anderson.address_api.core.services.impl;

import com.anderson.address_api.core.dtos.AddressExternalDTO;
import com.anderson.address_api.core.dtos.AddressRequestDTO;
import com.anderson.address_api.core.model.Address;
import com.anderson.address_api.core.repository.AddressRepository;
import com.anderson.address_api.core.services.AddressService;
import com.anderson.address_api.core.services.ConsultZipCode;

public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    private final ConsultZipCode consultZipCode;

    public AddressServiceImpl(AddressRepository repository, ConsultZipCode consultZipCode) {
        this.repository = repository;
        this.consultZipCode = consultZipCode;
    }

    @Override
    public void insert(AddressRequestDTO dto) {
        try {
            AddressExternalDTO dtoExternal = this.consultZipCode.getAddress(dto.zipCode());

            Address address = new Address(dtoExternal);
            address.setNumber(dto.number());
            if(dto.complement() != null) address.setComplement(dto.complement());

            this.repository.save(address);
        } catch (Exception e) {
            throw new RuntimeException("Zip code not found !");
        }
    }
}
