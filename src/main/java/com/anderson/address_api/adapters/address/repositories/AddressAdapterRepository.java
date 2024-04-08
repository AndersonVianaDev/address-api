package com.anderson.address_api.adapters.address.repositories;

import com.anderson.address_api.adapters.address.entity.AddressAdapter;
import com.anderson.address_api.core.model.Address;
import com.anderson.address_api.core.repository.AddressRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AddressAdapterRepository implements AddressRepository {

    private final JpaAddressAdapterRepository repository;

    public AddressAdapterRepository(JpaAddressAdapterRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Address address) {
        AddressAdapter addressAdapter = new AddressAdapter(address);

        this.repository.save(addressAdapter);
    }

    @Override
    public Optional<Address> findByNumberAndZipCode(String number, String zipCode) {
        Optional<AddressAdapter> addressAdapter = this.repository.findByNumberAndZipCode(number, zipCode);

        return addressAdapter.map(AddressAdapter::toAddress);
    }
}
