package com.anderson.address_api.core.dtos;

public record AddressResponseDTO(String zipCode, String locality, String uf, String neighborhood, String complement, String number) {
}
