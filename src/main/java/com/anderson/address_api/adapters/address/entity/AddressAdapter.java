package com.anderson.address_api.adapters.address.entity;

import com.anderson.address_api.core.model.Address;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_addresses")
public class AddressAdapter {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String zipCode;
    private String locality;
    private String uf;
    private String neighborhood;
    private String complement;
    private String number;

    @CreationTimestamp
    private Instant created_at;
    @UpdateTimestamp
    private Instant updated_at;

    public AddressAdapter() {
    }

    public AddressAdapter(Address address) {
        this.zipCode = address.getZipCode();
        this.locality = address.getLocality();
        this.uf = address.getUf();
        this.neighborhood = address.getNeighborhood();
        this.complement = address.getComplement();
        this.number = address.getNumber();
    }

    public Address toAddress() {
        return new Address(this.id, this.zipCode, this.locality, this.uf, this.neighborhood, this.complement, this.number, this.created_at, this.updated_at);
    }

}
