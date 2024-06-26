package com.anderson.address_api.core.services.impl;

import com.anderson.address_api.core.dtos.AddressExternalDTO;
import com.anderson.address_api.core.dtos.AddressRequestDTO;
import com.anderson.address_api.core.dtos.AddressUpdateDTO;
import com.anderson.address_api.core.model.Address;
import com.anderson.address_api.core.repository.AddressRepository;
import com.anderson.address_api.core.services.AddressService;
import com.anderson.address_api.core.services.ConsultZipCode;
import com.anderson.address_api.core.util.ValidateZipCode;
import com.anderson.address_api.shared.exceptions.AlreadyRegisteredException;
import com.anderson.address_api.shared.exceptions.InvalidDataException;
import com.anderson.address_api.shared.exceptions.NotFoundException;

import java.util.UUID;
import java.util.logging.Logger;

import static com.anderson.address_api.shared.exceptions.Constants.*;

public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    private final ConsultZipCode consultZipCode;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public AddressServiceImpl(AddressRepository repository, ConsultZipCode consultZipCode) {
        this.repository = repository;
        this.consultZipCode = consultZipCode;
    }

    @Override
    public Address insert(AddressRequestDTO dto) {
        //validate that there are only numeric digits and that the length is 8 digits
        if(!ValidateZipCode.validate(dto.zipCode())) throw new InvalidDataException(INVALID_ZIP_CODE_FORMAT);

        //checking if you already have an address with the same zip code and house number already registered
        if(this.repository.findByNumberAndZipCode(dto.number(), dto.zipCode()).isPresent()) throw new AlreadyRegisteredException(ADDRESS_ALREADY_REGISTERED);

        try {
            AddressExternalDTO dtoExternal = this.consultZipCode.getAddress(dto.zipCode());

            Address address = new Address(dtoExternal);

            //saving the zip code without the hyphen
            address.setZipCode(dto.zipCode());

            address.setNumber(dto.number());
            if(dto.complement() != null) address.setComplement(dto.complement());

            logger.info("Saving address with zip code " + address.getZipCode() + " and house number " + address.getNumber());
            return this.repository.save(address);
        } catch (NotFoundException e) {
            logger.severe("Zip code does not exist: " + dto.zipCode());
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            logger.severe("Error in serialization");
            throw new IllegalStateException(UNEXPECTED_ERROR);
        }
    }

    @Override
    public Address findById(UUID id) {
        Address address = this.repository.findById(id).orElseThrow(() -> {
            logger.severe("Address with id " + id + " not found !");
            throw new NotFoundException(ADDRESS_NOT_FOUND);
        });

        return address;
    }

    @Override
    public void delete(UUID id) {
        Address address = this.findById(id);

        this.repository.delete(address);
    }

    @Override
    public Address update(UUID id, AddressUpdateDTO dto) {
        Address address = this.findById(id);

        if(!dto.number().equals(address.getNumber()) && dto.number() != null) address.setNumber(dto.number());
        if(!dto.complement().equals(address.getComplement()) && dto.complement() != null) address.setComplement(dto.complement());

        return this.repository.update(address);
    }

}
