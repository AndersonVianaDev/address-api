package com.anderson.address_api.api.external;

import com.anderson.address_api.core.dtos.AddressExternalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCep", url = "https://viacep.com.br/ws/")
public interface ViaCep {

    @GetMapping("{zipCode}/json")
    AddressExternalDTO getAddress(@PathVariable String zipCode);
}
