package com.anderson.address_api.core.services;

import com.anderson.address_api.core.dtos.AddressExternalDTO;

public interface ConsultZipCode {

    AddressExternalDTO getAddress(String zipCode) throws Exception;
}
