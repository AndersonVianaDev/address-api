package com.anderson.address_api.adapters.address.repositories;

import com.anderson.address_api.adapters.address.entity.AddressAdapter;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaAddressAdapterRepository extends JpaRepository<AddressAdapter, UUID> {

    @Query("SELECT ad FROM AddressAdapter ad WHERE ad.number=:number AND ad.zipCode=:zipCode")
    Optional<AddressAdapter> findByNumberAndZipCode(@Param("number") String number, @Param("zipCode") String zipCode);
}
