package com.anderson.address_api.core.repository;

import com.anderson.address_api.core.model.Address;

import java.util.Optional;

public interface AddressRepository {

    void save(Address address);

    Optional<Address> findByNumberAndZipCode(String number, String zipCode);
}
