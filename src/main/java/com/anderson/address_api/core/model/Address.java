package com.anderson.address_api.core.model;

import com.anderson.address_api.core.dtos.AddressExternalDTO;

import java.time.Instant;
import java.util.UUID;

public class Address {

    private UUID id;
    private String zipCode;
    private String locality;
    private String uf;
    private String neighborhood;
    private String complement;
    private String number;
    private Instant created_at;
    private Instant updated_at;

    public Address() {
    }
    public Address(UUID id, String zipCode, String locality, String uf, String neighborhood, String complement, String number, Instant created_at, Instant updated_at) {
        this.id = id;
        this.zipCode = zipCode;
        this.locality = locality;
        this.uf = uf;
        this.neighborhood = neighborhood;
        this.complement = complement;
        this.number = number;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    public Address(AddressExternalDTO dto) {
        this.zipCode = dto.cep();
        this.locality = dto.localidade();
        this.uf = dto.uf();
        this.neighborhood = dto.bairro();
    }
}
