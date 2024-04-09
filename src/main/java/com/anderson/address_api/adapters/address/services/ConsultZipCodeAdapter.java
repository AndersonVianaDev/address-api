package com.anderson.address_api.adapters.address.services;

import com.anderson.address_api.api.external.ViaCep;
import com.anderson.address_api.core.dtos.AddressExternalDTO;
import com.anderson.address_api.core.services.ConsultZipCode;
import com.anderson.address_api.shared.exceptions.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import static com.anderson.address_api.shared.exceptions.Constants.ZIP_CODE_DOES_NOT_EXIST;

@Service
public class ConsultZipCodeAdapter implements ConsultZipCode {

    private final ViaCep viaCep;

    private final Jedis jedis;

    private final ObjectMapper objectMapper;

    private static final int EXPIRATION_TIME = 24 * 60 * 60;

    public ConsultZipCodeAdapter(ViaCep viaCep, Jedis jedis, ObjectMapper objectMapper) {
        this.viaCep = viaCep;
        this.jedis = jedis;
        this.objectMapper = objectMapper;
    }

    @Override
    public AddressExternalDTO getAddress(String zipCode) throws Exception {
        var value = jedis.get(zipCode);

        if(value != null) return objectMapper.readValue(value, AddressExternalDTO.class);

        AddressExternalDTO dto = this.viaCep.getAddress(zipCode);

        if(dto.cep() == null) throw new NotFoundException(ZIP_CODE_DOES_NOT_EXIST);

        jedis.setex(zipCode, EXPIRATION_TIME, objectMapper.writeValueAsString(dto));

        return dto;
    }
}
