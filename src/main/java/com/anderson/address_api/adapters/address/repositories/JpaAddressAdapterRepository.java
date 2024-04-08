package com.anderson.address_api.adapters.address.repositories;

import com.anderson.address_api.adapters.address.entity.AddressAdapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaAddressAdapterRepository extends JpaRepository<AddressAdapter, UUID> {
}
