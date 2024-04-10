package com.anderson.address_api.adapters.address.repositories;

import com.anderson.address_api.adapters.address.entity.AddressAdapter;
import com.anderson.address_api.core.model.Address;
import com.anderson.address_api.core.repository.AddressRepository;
import com.anderson.address_api.shared.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class AddressAdapterRepository implements AddressRepository {

    private final JpaAddressAdapterRepository repository;

    public AddressAdapterRepository(JpaAddressAdapterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address save(Address address) {
        AddressAdapter addressAdapter = new AddressAdapter(address);

        return this.repository.save(addressAdapter).toAddress();
    }

    @Override
    public Optional<Address> findByNumberAndZipCode(String number, String zipCode) {
        Optional<AddressAdapter> addressAdapter = this.repository.findByNumberAndZipCode(number, zipCode);

        return addressAdapter.map(AddressAdapter::toAddress);
    }

    @Override
    public Optional<Address> findById(UUID id) {
        Optional<AddressAdapter> addressAdapter = this.repository.findById(id);

        return addressAdapter.map(AddressAdapter::toAddress);
    }

    @Override
    public void delete(Address address) {
        AddressAdapter addressAdapter = this.repository.findById(address.getId()).orElseThrow(() -> new NotFoundException("Address not found !"));

        this.repository.delete(addressAdapter);
    }

    @Override
    public Address update(Address address) {
        AddressAdapter addressAdapter = this.repository.findById(address.getId()).orElseThrow(() -> new NotFoundException("Address not found !"));

        addressAdapter.setComplement(address.getComplement());
        addressAdapter.setNumber(address.getNumber());

        return this.repository.save(addressAdapter).toAddress();
    }
}
